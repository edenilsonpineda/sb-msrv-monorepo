package com.technicaltest.springboot.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.technicaltest.springboot.auth.model.security.User;
import com.technicaltest.springboot.auth.model.token.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {
	@Query(value = """
			select t from Token t inner join User u\s
			on t.user.id = u.id\s
			where u.id = :id and (t.expired = false or t.revoked = false)\s
			""")
	List<Token> findAllValidTokenByUser(Long id);

	Optional<Token> findByTokenValue(String token);
	
	Optional<Token> findOneByUser(User user);
	
	@Query(value = """
			select t from Token t inner join User u\s
			on t.user.id = u.id\s
			where u.email = :userEmail and t.tokenValue = :tokenValue \s
			""")
	Optional<Token> findByUserEmailAndTokenValue(@Param(value = "userEmail") String userEmail, @Param(value = "tokenValue") String tokenValue);
}
