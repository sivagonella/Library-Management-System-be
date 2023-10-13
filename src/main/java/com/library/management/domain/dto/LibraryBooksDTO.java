package com.library.management.domain.dto;

import com.library.management.domain.entity.Author;
import com.library.management.domain.entity.LibraryBook;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class LibraryBooksDTO implements Serializable {

    private List<LibraryBook> libraryBooks;

    private int pageNo;

    private int pageSize;

    private long totalElements;

    private int totalPages;

    private boolean isLast;
}
