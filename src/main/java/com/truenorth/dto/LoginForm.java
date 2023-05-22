package com.truenorth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class LoginForm implements Serializable {
    @JsonProperty("username")
    private String username;

    @JsonProperty("password")
    private String password;

    // Default constructor (required for JSON parsing)
    public LoginForm() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
