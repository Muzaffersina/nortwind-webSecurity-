package com.turkcell.northwind.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class CreateUserDto {

	private String id;
	
	private String email;


}
