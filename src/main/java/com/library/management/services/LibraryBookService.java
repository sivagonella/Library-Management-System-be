package com.library.management.services;

import com.library.management.domain.dto.LibraryBooksDTO;
import com.library.management.domain.entity.LibraryBook;
import com.library.management.domain.entity.enums.BorrowedStatus;

import java.util.List;

public interface LibraryBookService {
    LibraryBooksDTO getBooks(int pageNo, int pageSize);

    LibraryBook addBook(LibraryBook libraryBook);

    boolean updateBookCount(int bookId, int borrowedQuantity, BorrowedStatus borrowedStatus);

    LibraryBook getBookById(int bookId);
}
