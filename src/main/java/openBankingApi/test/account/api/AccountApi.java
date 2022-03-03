package openBankingApi.test.account.api;

import lombok.RequiredArgsConstructor;
import openBankingApi.test.account.dto.AccountBalanceDto;
import openBankingApi.test.account.dto.AccountInfoListDto;
import openBankingApi.test.account.service.AccountService;
import openBankingApi.test.basic.exception.BusinessException;
import openBankingApi.test.basic.response.ResponseService;
import openBankingApi.test.basic.response.SingleResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/account")
@RestController
@RequiredArgsConstructor
public class AccountApi {

	private final AccountService accountService;
	private final ResponseService responseService;

	@GetMapping("/balance/fin_num")
	public SingleResult<AccountBalanceDto> getAccountBalanceByFinNum(
			@RequestParam(name = "userId") String userId,
			@RequestParam(name = "fintechUseNum") String fintechUseNum
	) {
		try {
			AccountBalanceDto accountBalance = accountService.getAccountBalance(userId, fintechUseNum);
			return responseService.getSingleResult(accountBalance);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}

	@GetMapping("/list")
	public SingleResult<AccountInfoListDto> getAccountList(
			@RequestParam String userId,
			@RequestParam String includeCancelYn,
			@RequestParam String sortOrder
	) {
		try {
			return responseService.getSingleResult(
					accountService.getAccountList(userId, includeCancelYn, sortOrder)
			);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
}
