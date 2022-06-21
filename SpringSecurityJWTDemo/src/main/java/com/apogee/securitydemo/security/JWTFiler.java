package com.apogee.securitydemo.security;

import com.apogee.securitydemo.services.CustomeUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JWTFiler extends OncePerRequestFilter {

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private CustomeUserDetailService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String username = null;
        String token = null;

        if ( null != request.getHeader("Authorization")  && request.getHeader("Authorization").contains("Bearer ")){
            token = request.getHeader("Authorization").substring(7);
            username = jwtUtils.extractUsername(token);
        }

        if( null != username && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if(jwtUtils.validateToken(token,userDetails)){

                UsernamePasswordAuthenticationToken usernamePasswordToken = new UsernamePasswordAuthenticationToken(userDetails,null,new ArrayList<>());
                usernamePasswordToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordToken);
            }
        }
        filterChain.doFilter(request,response);

    }
}
