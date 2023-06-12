package com.technicaltest.sb.orders.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.technicaltest.sb.orders.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	
	boolean existsByEmail(String email);
}
