package com.library.management.domain.dto;

import com.library.management.domain.entity.Author;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LibraryBooksDTO implements Serializable {

    private int id;

    private String name;

    private List<Author> authors;

    private int quantity;
}
