package com.turkcell.northwind.business.abstracts;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.turkcell.northwind.business.dtos.CreateUserDto;
import com.turkcell.northwind.business.dtos.UserDto;
import com.turkcell.northwind.business.requests.CreateUserRequest;

public interface UserService extends UserDetailsService {
	
	CreateUserDto createUser(CreateUserRequest createUserRequest);
	
	UserDto getUserDetailsByEmail(String email);
}
