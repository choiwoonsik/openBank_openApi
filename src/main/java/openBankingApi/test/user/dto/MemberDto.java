package openBankingApi.test.user.dto;

import lombok.Builder;
import lombok.Data;
import openBankingApi.test.user.entity.Member;

@Data
@Builder
public class MemberDto {
	private String userId;
	private Long userSeqNo;

	public Member toEntity() {
		return Member.builder()
				.userId(userId)
				.userSeqNo(userSeqNo)
				.build();
	}
}
