package com.technicaltest.sb.orders.model;

import com.technicaltest.sb.orders.model.base.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user")
@EqualsAndHashCode(callSuper = false)
public class User extends BaseEntity{
	private static final long serialVersionUID = 1L;

	private String firstName;
	private String lastName;
	private String email;

	private String username;
	private String password;
	
	@Transient
	private boolean userAlreadyExists;
}
