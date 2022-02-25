package openBankingApi.test.oauth.repository;

import openBankingApi.test.oauth.entity.OauthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OauthTokenRepository extends JpaRepository<OauthToken, Long> {
	Optional<OauthToken> findByUser_seq_no(Long user_seq_no);
}
