package com.library.management.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorDTO implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String bio;
}
