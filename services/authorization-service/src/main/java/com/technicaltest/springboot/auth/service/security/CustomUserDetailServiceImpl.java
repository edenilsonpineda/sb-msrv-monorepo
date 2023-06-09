package com.technicaltest.springboot.auth.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.technicaltest.springboot.auth.commons.Constants;
import com.technicaltest.springboot.auth.repository.UserRepository;
import com.technicaltest.springboot.auth.service.interfaces.ICustomUserDetailService;
import com.technicaltest.springboot.auth.utils.EnvironmentHelper;


@Service
public class CustomUserDetailServiceImpl extends EnvironmentHelper implements ICustomUserDetailService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(getEnv().getProperty(Constants.NOTFOUND_MESSAGE), 
						username)));
	}

}
