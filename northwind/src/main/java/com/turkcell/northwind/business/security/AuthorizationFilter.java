package com.turkcell.northwind.business.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.turkcell.northwind.business.abstracts.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class AuthorizationFilter extends OncePerRequestFilter {	

	@Autowired	
	private UserService userService;	
		

	public AuthorizationFilter(UserService userService) {		
		this.userService = userService;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorizationHeader = request.getHeader("Authorization");
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

			String token = authorizationHeader.substring("Bearer ".length());

			try {
				/*
				Jwts.parser().setSigningKey("mysupersecretkeymysupersecretkeymysupersecretkey").parseClaimsJws(token);
				Claims claims = Jwts.parser().setSigningKey("mysupersecretkeymysupersecretkeymysupersecretkey").parseClaimsJws(token).getBody();				
				String userName = claims.getSubject();
				*/
				validateToken(token);
				String userName = getUserIdFromJwt(token);
				UserDetails user = this.userService.loadUserByUsername(userName);
				if(user != null) {					
					UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getUsername(),null,null);
					auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(auth); 
				}

			} catch (IllegalArgumentException e) {	
				
				return;				
			}
			

		}
		filterChain.doFilter(request, response);
	}
	public String getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey("mysupersecretkeymysupersecretkeymysupersecretkey").parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	public boolean validateToken (String token) {
		try {
			Jwts.parser().setSigningKey("mysupersecretkeymysupersecretkeymysupersecretkey").parseClaimsJws(token);
			return !isTokenExpired(token);
			
		}catch (SignatureException e) {
			return false;
		}catch (MalformedJwtException e) {
			return false;
		}catch (ExpiredJwtException e) {
			return false;
		}catch (UnsupportedJwtException e) {
			return false;
		}catch (IllegalArgumentException e) {
			return false;
		}
	}	
	
	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser().setSigningKey("mysupersecretkeymysupersecretkeymysupersecretkey").parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}
}
