package openBankingApi.test.account.api;

import lombok.RequiredArgsConstructor;
import openBankingApi.test.account.dto.AccountBalanceDto;
import openBankingApi.test.account.service.AccountService;
import openBankingApi.test.basic.exception.BusinessException;
import openBankingApi.test.basic.response.ResponseService;
import openBankingApi.test.basic.response.SingleResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountApi {

	private final AccountService accountService;
	private final ResponseService responseService;

	@GetMapping("/account/balance/fin_num")
	public SingleResult<AccountBalanceDto> getAccountBalanceByFinNum(
			@RequestParam(name = "clientName") String clientName,
			@RequestParam(name = "clientMobile") String clientMobile,
			@RequestParam(name = "fintechUseNum") String fintechUseNum
	) {
		try {
			AccountBalanceDto accountBalance = accountService.getAccountBalance(clientName, clientMobile, fintechUseNum);
			return responseService.getSingleResult(accountBalance);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
}
