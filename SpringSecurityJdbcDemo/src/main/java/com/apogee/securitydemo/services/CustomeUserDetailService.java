package com.apogee.securitydemo.services;

import com.apogee.securitydemo.entities.User;
import com.apogee.securitydemo.repositories.UserRepository;
import com.apogee.securitydemo.security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomeUserDetailService implements UserDetailsService {


    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Optional<User> userOptional = userRepo.findByUsername(username);

       userOptional.orElseThrow(()->  new UsernameNotFoundException("Username not registered before, Kindly register and login again "));

        return userOptional.map(MyUserDetails::new).get();
    }
}
