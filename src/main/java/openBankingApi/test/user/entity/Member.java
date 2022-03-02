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
	private String userName;

	@Column
	private String userMobile;

	@Column
	private Long userSeqNo;

	public MemberDto toDto() {
		return MemberDto.builder()
				.userName(userName)
				.userMobile(userMobile)
				.userSeqNo(userSeqNo)
				.build();
	}
}