package com.library.management.domain.dto;

import com.library.management.domain.entity.LibraryBook;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AuthorDTO implements Serializable {
    private int id;
    private String name;
    private String email;
    private String bio;
    private List<LibraryBook> books;
}
