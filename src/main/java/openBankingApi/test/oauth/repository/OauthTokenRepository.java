package openBankingApi.test.oauth.repository;

import openBankingApi.test.oauth.entity.OauthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthTokenRepository extends JpaRepository<OauthToken, Long> {
}
