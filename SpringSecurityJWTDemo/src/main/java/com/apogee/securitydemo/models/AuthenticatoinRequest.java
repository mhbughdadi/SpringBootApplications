package com.apogee.securitydemo.models;

public class AuthenticatoinRequest {

    private String username;
    private String password;

    public AuthenticatoinRequest(){

    }

    public AuthenticatoinRequest(String username, String password){
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
