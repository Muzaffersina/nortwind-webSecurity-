package com.turkcell.northwind.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class UserEntity {
	
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "email",unique = true)
	private String email;
	
	@Column(name = "encryptedPassword")
	private String encryptedPassword;

}
