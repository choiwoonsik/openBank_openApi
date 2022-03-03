package openBankingApi.test.user.repository;

import openBankingApi.test.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
	Boolean existsByUserSeqNo(Long userSeqNo);

	Optional<Member> findByUserSeqNo(Long userSeqNo);

	Optional<Member> findByUserId(String userId);
}