package com.turkcell.northwind.business.concretes;

import java.util.ArrayList;
import java.util.UUID;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.turkcell.northwind.business.abstracts.UserService;
import com.turkcell.northwind.business.dtos.CreateUserDto;
import com.turkcell.northwind.business.dtos.UserDto;
import com.turkcell.northwind.business.requests.CreateUserRequest;
import com.turkcell.northwind.core.utilitys.mapping.ModelMapperService;
import com.turkcell.northwind.dataAccess.abstracts.UserDao;
import com.turkcell.northwind.entities.concretes.UserEntity;

@Service
public class UserManager implements UserService {

	private UserDao userDao;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private ModelMapperService modelMapperService;

	public UserManager(UserDao userDao, BCryptPasswordEncoder bCryptPasswordEncoder,
			ModelMapperService modelMapperService) {
		this.userDao = userDao;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public CreateUserDto createUser(CreateUserRequest createUserRequest) {

		createUserRequest.setId(UUID.randomUUID().toString());
		createUserRequest.setEncryptedPassword(this.bCryptPasswordEncoder.encode(createUserRequest.getPassword()));
		UserEntity user = this.modelMapperService.forRequest().map(createUserRequest, UserEntity.class);
		this.userDao.save(user);

		return this.modelMapperService.forDto().map(user, CreateUserDto.class);
	}

	@Override
	public UserDto getUserDetailsByEmail(String email) {
		UserEntity user = this.userDao.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return this.modelMapperService.forDto().map(user, UserDto.class);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UserEntity userEntity = this.userDao.findByEmail(username);	

		if (userEntity == null) {			
			throw new UsernameNotFoundException(username);
		}
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true,
				new ArrayList<>());
	}

}
