package com.turkcell.northwind.business.requests;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {
	
	@JsonIgnore
	private String id;
	
	@NotNull
	@Size(min=2)
	private String email;
	
	@NotNull
	@Size(min=2,max=16)
	private String password;

	@JsonIgnore
	private String encryptedPassword;


}
