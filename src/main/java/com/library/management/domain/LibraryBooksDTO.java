package com.library.management.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LibraryBooksDTO implements Serializable {

    private Integer id;

    private String name;

    private List<Author> authors;

    private Integer quantity;
}
