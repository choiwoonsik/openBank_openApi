package openBankingApi.test.user.dto;

import lombok.Builder;
import lombok.Data;
import openBankingApi.test.user.entity.Member;

@Data
@Builder
public class MemberDto {
	private String userName;
	private String userMobile;
	private Long userSeqNo;

	public Member toEntity() {
		return Member.builder()
				.userName(userName)
				.userMobile(userMobile)
				.userSeqNo(userSeqNo)
				.build();
	}
}
