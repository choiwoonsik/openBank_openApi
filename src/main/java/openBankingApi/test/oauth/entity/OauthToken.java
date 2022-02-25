package openBankingApi.test.oauth.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OauthToken {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long seq;

	@Column(columnDefinition = "TEXT", length = 400)
	private String access_token;

	@Column
	private String token_type;

	@Column
	private String expires_in;

	@Column(columnDefinition = "TEXT", length = 400)
	private String refresh_token;

	@Column
	private String scope;

	@Column
	private Long user_seq_no;
}
