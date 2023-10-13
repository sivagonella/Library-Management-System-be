package com.library.management.services;

import com.library.management.domain.entity.LibraryBook;
import com.library.management.domain.entity.enums.BorrowedStatus;

import java.util.List;

public interface LibraryBookService {
    List<LibraryBook> getBooks();

    LibraryBook addBook(LibraryBook libraryBook);

    boolean updateBookCount(int bookId, int borrowedQuantity, BorrowedStatus borrowedStatus);

    LibraryBook getBookById(int bookId);
}
