package net.code.flightplan.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.code.flightplan.repository.UserRepository;


public class UsersDetailsService implements UserDetailsService {
 
    @Autowired
    UserRepository userRepository;
    
    
     
    @Override
    public UserDetails loadUserByUsername(String email){
                
        return null;
    }
    
    

	
}