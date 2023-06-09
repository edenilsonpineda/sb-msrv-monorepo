package com.technicaltest.springboot.auth.model.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {
	ADMIN_READ("admin:read"),
    CUSTOMER_READ("customer:read"),
    CUSTOMER_UPDATE("customer:update"),
    CUSTOMER_DELETE("customer:delete"),
    CUSTOMER_CREATE("customer:create");
	
	@Getter
	private final String permissionDetail;
}
