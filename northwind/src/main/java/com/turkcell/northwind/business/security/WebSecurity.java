package com.turkcell.northwind.business.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.turkcell.northwind.business.abstracts.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

	private UserService userService;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userService = userService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		  http.csrf().disable();
		  http.authorizeRequests().antMatchers("/login").hasIpAddress("127.0.0.1").and().addFilter(getAuthenticationFilter());
		  http.headers().frameOptions().disable();
		*/
		
		http.csrf().disable();		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);		
		http.authorizeRequests().antMatchers("/api/login/**","/users/createuser/**").permitAll();		
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(getAuthenticationFilter());
		http.addFilterBefore(new AuthorizationFilter(userService),UsernamePasswordAuthenticationFilter.class);
		
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

		authenticationManagerBuilder.userDetailsService(this.userService).passwordEncoder(bCryptPasswordEncoder);

	}

	private AuthenticationFilter getAuthenticationFilter() throws Exception {

		AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager(), userService);
		authenticationFilter.setFilterProcessesUrl("/api/login");
		return authenticationFilter;
	}

}

