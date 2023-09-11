package com.library.management.domain;

import lombok.Data;

@Data
public class NewUserDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

    private Integer isCorrectPassword = 1;
}
