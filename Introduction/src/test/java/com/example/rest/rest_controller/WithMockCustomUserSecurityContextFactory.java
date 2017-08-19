package com.example.rest.rest_controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import lombok.Data;

public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockCustomUser> {
	
	@Override
	public SecurityContext createSecurityContext(WithMockCustomUser customUser) {
	SecurityContext context = SecurityContextHolder.createEmptyContext();
	
	List<GrantedAuthority> authorities = new ArrayList<>();
	
	for (String role : customUser.roles()) {
	    authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
	}
	
	CustomUserDetails principal = new CustomUserDetails(customUser.username(), customUser.password(), authorities);
	Authentication auth = new UsernamePasswordAuthenticationToken(principal,"password", principal.getAuthorities());
	context.setAuthentication(auth);
	return context;
	}

		@Data
		public class CustomUserDetails extends User {
		private String email;
		public CustomUserDetails(String userno
				  			     , String password
				  			     , Collection<? extends GrantedAuthority> authorities) {
		    super(userno, password, authorities);
		    this.email = email;
		}
	}
}