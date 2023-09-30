package com.library.management.domain;

import lombok.Data;

@Data
public class UserDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

    private Integer isCorrectPassword = 1;
}
