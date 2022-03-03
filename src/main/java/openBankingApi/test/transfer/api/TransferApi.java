package openBankingApi.test.transfer.api;

import lombok.RequiredArgsConstructor;
import openBankingApi.test.basic.exception.BusinessException;
import openBankingApi.test.basic.exception.ExMessage;
import openBankingApi.test.basic.response.ResponseService;
import openBankingApi.test.basic.response.SingleResult;
import openBankingApi.test.transfer.dto.TransferWdFinNumReq;
import openBankingApi.test.transfer.dto.TransferWdFinNumRes;
import openBankingApi.test.transfer.service.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TransferApi {

	private final TransferService transferService;
	private final ResponseService responseService;

	@PostMapping("transfer/withdraw/fin_num")
	public SingleResult<TransferWdFinNumRes> transferWithdrawByFinNum(
			@RequestBody @ModelAttribute TransferWdFinNumReq req
	) {
		try {
			return responseService.getSingleResult(transferService.transferWithdrawByFinNum(req));
		} catch (Exception e) {
			throw new BusinessException(ExMessage.UNDEFINED_ERROR);
		}
	}
}
