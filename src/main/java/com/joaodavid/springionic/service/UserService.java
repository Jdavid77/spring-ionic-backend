package com.joaodavid.springionic.service;

import org.springframework.security.core.context.SecurityContextHolder;

import com.joaodavid.springionic.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch(Exception e) {
			return null;
		}
		
	}

}
