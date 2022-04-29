package com.turkcell.northwind.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.turkcell.northwind.business.abstracts.UserService;
import com.turkcell.northwind.business.dtos.CreateUserDto;
import com.turkcell.northwind.business.requests.CreateUserRequest;

@RestController
@RequestMapping("/users")
public class UsersController {

	private UserService userService;
	
	
	public UsersController(UserService userService) {
		this.userService= userService;
	}
	
	@PostMapping("/createuser")
	public CreateUserDto createUser(@RequestBody CreateUserRequest createUserRequest) {
		return this.userService.createUser(createUserRequest);		
	}
}

