package openBankingApi.test.oauth.repository;

import openBankingApi.test.oauth.entity.OauthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OauthTokenRepository extends JpaRepository<OauthToken, Long> {
	Optional<OauthToken> findByUserSeqNo(Long userSeqNo);

	Optional<OauthToken> findByUserId(String userId);

	Optional<OauthToken> findByUserNameAndUserMobile(String userName, String userMobile);
}
