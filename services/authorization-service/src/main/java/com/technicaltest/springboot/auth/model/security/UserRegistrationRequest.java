package com.technicaltest.springboot.auth.model.security;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationRequest {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Role role;
}
