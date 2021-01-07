package net.code.flightplan.model;

import java.util.*;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
public class UsersDetails implements UserDetails {
    private User users;
     
    public UsersDetails(User users) {
        this.users = users;
    }
    
   
 
   
 
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Role> roles = users.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return authorities;
    }
 
    @Override
    public String getPassword() {
        return users.getPassword();
    }
 
    @Override
    public String getUsername() {
        return users.getEmail();
    }
 
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
 
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
 
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
 
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public int getId() {
        return this.users.getId();
    }
   
   
    
    
}