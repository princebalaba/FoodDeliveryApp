package com.hibernate.demo.authentication;

import javax.validation.constraints.Email;

import lombok.Data;

@Data
public class Authentication {
	
	private String password;
	@Email
	private String email;

}
