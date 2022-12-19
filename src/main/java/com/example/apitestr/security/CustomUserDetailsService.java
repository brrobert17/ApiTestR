package com.example.apitestr.security;

import com.example.apitestr.model.User;
import com.example.apitestr.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepo.findUserByUsername(username);
        if (optionalUser.isPresent()) {
            return CustomUserDetails.build(optionalUser.get());
        }
        throw new UsernameNotFoundException("user by: " + username + "not found");
    }
}
