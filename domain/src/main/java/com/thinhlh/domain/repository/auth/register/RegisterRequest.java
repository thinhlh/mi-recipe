package com.thinhlh.domain.repository.auth.register;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("email")
    private String email;
    @SerializedName("name")
    private String name;
    @SerializedName("password")
    private String password;
    @SerializedName("role")
    private String role = "guest";

    public RegisterRequest(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }
}
