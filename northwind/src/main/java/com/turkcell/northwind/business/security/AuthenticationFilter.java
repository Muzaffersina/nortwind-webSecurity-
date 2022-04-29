package com.turkcell.northwind.business.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.turkcell.northwind.business.abstracts.UserService;
import com.turkcell.northwind.business.dtos.UserDto;
import com.turkcell.northwind.business.requests.LoginRequest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private UserService userService;

	public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {

		this.userService = userService;
		super.setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		try {
			LoginRequest credidentals = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
															
			return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
					credidentals.getEmail(), credidentals.getPassword(), new ArrayList<>()));
		} catch (IOException exception) {

			throw new RuntimeException();
		}
	}

	@Override
	public void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String userName = ((User) authResult.getPrincipal()).getUsername();
		UserDto userDetails = this.userService.getUserDetailsByEmail(userName);
		String token = Jwts.builder().setSubject(userDetails.getEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 5 )) // 5 dakika gecerli token
				.signWith(SignatureAlgorithm.HS512, "mysupersecretkeymysupersecretkeymysupersecretkey")
				.setId(userDetails.getId())	
				.setIssuer(request.getRequestURL().toString())
				.compact();
		response.addHeader("token", token);
		response.addHeader("userId", userDetails.getId());

	}

}
