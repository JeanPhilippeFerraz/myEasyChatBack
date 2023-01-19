package com.easychat.core.Controller.dto;

import lombok.Data;

@Data
public class UserDto {


    private Integer id;
    private String username;
    private String email;
    private String password;

    public UserDto() {
    }

    public UserDto(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
