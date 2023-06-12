package com.technicaltest.sb.orders.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.technicaltest.sb.orders.model.User;
import com.technicaltest.sb.orders.repository.UserRepository;
import com.technicaltest.sb.orders.service.interfaces.UserService;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findUserByEmail(String email) {

		return userRepository.findByEmail(email);
	}

}
