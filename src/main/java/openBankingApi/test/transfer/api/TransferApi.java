package openBankingApi.test.transfer.api;

import lombok.RequiredArgsConstructor;
import openBankingApi.test.basic.exception.BusinessException;
import openBankingApi.test.basic.exception.ExMessage;
import openBankingApi.test.oauth.entity.OauthToken;
import openBankingApi.test.oauth.repository.OauthTokenRepository;
import openBankingApi.test.transfer.dto.TransferWithdrawFinNumReq;
import openBankingApi.test.transfer.service.TransferService;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class TransferApi {

	private final TransferService transferService;

	@PostMapping("transfer/withdraw/fin_num")
	public void transferWithdrawByFinNum(
			@RequestBody @ModelAttribute TransferWithdrawFinNumReq req
	) {
		transferService.transferWithdrawByFinNum(req);

	}
}
