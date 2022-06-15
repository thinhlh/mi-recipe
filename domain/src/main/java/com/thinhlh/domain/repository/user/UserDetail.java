package com.thinhlh.domain.repository.user;

import com.google.gson.annotations.SerializedName;

public class UserDetail {
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("email")
    private String email;
    @SerializedName("enabled")
    private Boolean enabled;
    @SerializedName("avatar")
    private String avatar;
    @SerializedName("role")
    private String role;

    public String getId() {
        return id;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
