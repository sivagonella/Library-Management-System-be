package com.library.management.services;

import com.library.management.domain.entity.LibraryBook;
import com.library.management.domain.entity.enums.BorrowedStatus;

import java.util.List;

public interface LibraryBookService {
    List<LibraryBook> getBooks();

    LibraryBook addBook(LibraryBook libraryBook);

    boolean updateBookCount(Integer bookId, Integer borrowedQuantity, BorrowedStatus borrowedStatus);

    LibraryBook getBookById(Integer bookId);
}
