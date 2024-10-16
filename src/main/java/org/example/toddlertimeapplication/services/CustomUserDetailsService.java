package org.example.toddlertimeapplication.services;

import org.example.toddlertimeapplication.model.Parent;
import org.example.toddlertimeapplication.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ParentRepository parentRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Parent parent = parentRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Parent not found with email: " + email));
        return (UserDetails) parent;
    }
}
