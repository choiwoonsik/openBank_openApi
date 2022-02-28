package openBankingApi.test.user.entity;

import lombok.*;
import openBankingApi.test.user.dto.MemberDto;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, unique = true)
	private Long seq;

	@Column
	private String userId;

	@Column
	private Long userSeqNo;

	public MemberDto toDto() {
		return MemberDto.builder()
				.userId(userId)
				.userSeqNo(userSeqNo)
				.build();
	}
}