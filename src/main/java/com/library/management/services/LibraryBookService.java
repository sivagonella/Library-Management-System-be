package com.library.management.services;

import com.library.management.domain.LibraryBook;

import java.util.List;

public interface LibraryBookService {
    public List<LibraryBook> getBooks();

    public LibraryBook addBook(LibraryBook libraryBook);

}
