package com.example.app.service;

import com.example.domain.User;
import com.example.app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsService implements UserDetailsService {
	
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String UserNo) throws UsernameNotFoundException {
        User user = userRepository.findOne(UserNo);
        
        if (user == null) {
            throw new UsernameNotFoundException("The requested user is not found.");
        }
        
        return new LoginUserDetails(user);
    }
}