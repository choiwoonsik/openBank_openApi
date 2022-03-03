package openBankingApi.test.user.api;

import lombok.RequiredArgsConstructor;
import openBankingApi.test.basic.exception.BusinessException;
import openBankingApi.test.basic.response.ResponseService;
import openBankingApi.test.basic.response.SingleResult;
import openBankingApi.test.user.dto.UserInfoAndAccountList;
import openBankingApi.test.user.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
public class UserApi {

	private final MemberService memberService;
	private final ResponseService responseService;

	@GetMapping("/me")
	public SingleResult<UserInfoAndAccountList> getUserInfo(
			@RequestParam("userId") String userId
	) {
		try {
			return responseService.getSingleResult(memberService.getUserInfo(userId));
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(e.getMessage());
		}
	}
}
