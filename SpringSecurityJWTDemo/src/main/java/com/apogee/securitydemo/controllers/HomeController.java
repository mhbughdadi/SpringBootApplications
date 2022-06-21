package com.apogee.securitydemo.controllers;

import com.apogee.securitydemo.models.AuthenticationResponse;
import com.apogee.securitydemo.models.AuthenticatoinRequest;
import com.apogee.securitydemo.security.JWTUtils;
import com.apogee.securitydemo.services.CustomeUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(consumes = "application/json",produces = "application/json")
public class HomeController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomeUserDetailService userDetailsService;
    @Autowired
    private JWTUtils jwtUtil;

    @GetMapping("/")
    public String getGreating(){
        return "Welcome Everyone in our Website";
    }
    
    @GetMapping("/user")
    public String getGreatingUser(){
        return "Welcome User in our Website";
    }
    
    @GetMapping("/admin")
    public String getGreatingAdmin(){
        return "Welcome Admin in our Website";
    }

    @PostMapping(value = "/authentication",consumes = "application/json",produces = "application/json")
    ResponseEntity<?> authenticate(@RequestBody AuthenticatoinRequest request) throws Exception {

        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));

        }catch (BadCredentialsException e){
            throw new Exception("Bad credentials, Kindly retry with the write one");
        }

        final UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());

        final String token = jwtUtil.generateToken(user);

        AuthenticationResponse response = new AuthenticationResponse(token);

        return ResponseEntity.ok(response);
    }
    
}
