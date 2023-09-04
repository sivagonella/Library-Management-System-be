package com.library.management.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class LibraryBookDTO implements Serializable {

    private Integer bookID;

    private String bookName;

    private List<Author> authorNames;

    private Integer numberOfBooks;
}