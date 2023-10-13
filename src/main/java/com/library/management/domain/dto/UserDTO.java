package com.library.management.domain.dto;

import lombok.Data;

@Data
public class UserDTO {

    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String role;

    private int isCorrectPassword = 1;
}
