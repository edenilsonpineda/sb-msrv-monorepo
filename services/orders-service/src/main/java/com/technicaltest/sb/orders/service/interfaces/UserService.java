package com.technicaltest.sb.orders.service.interfaces;

import java.util.Optional;

import com.technicaltest.sb.orders.model.User;

public interface UserService {
	Optional<User> findUserByEmail(String email);
}
