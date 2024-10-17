package org.example.toddlertimeapplication.services;

import org.example.toddlertimeapplication.model.Parent;
import org.example.toddlertimeapplication.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ParentRepository parentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Parent parent = parentRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Parent not found with email: " + email));


        // Return the user details with prefixed role
        return new User(parent.getEmail(), parent.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_PARENT")));
    }

}

