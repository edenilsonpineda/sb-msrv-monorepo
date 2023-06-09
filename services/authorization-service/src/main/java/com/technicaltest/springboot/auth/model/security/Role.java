package com.technicaltest.springboot.auth.model.security;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Role {
	USER(Collections.emptySet()),
	CUSTOMER(
			Set.of(
				Permission.CUSTOMER_CREATE,
				Permission.CUSTOMER_READ,
				Permission.CUSTOMER_DELETE,
				Permission.CUSTOMER_UPDATE
			)
	);
	
	@Getter
	private final Set<Permission> permissions;

	  public List<SimpleGrantedAuthority> getAuthorities() {
	    var authorities = getPermissions()
	            .stream()
	            .map(permission -> new SimpleGrantedAuthority(permission.getPermissionDetail()))
	            .collect(Collectors.toList());
	    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
	    return authorities;
	  }
}
