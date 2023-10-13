package com.library.management.services;

import com.library.management.domain.entity.UserBook;

import java.util.List;

public interface UserBookService {
    boolean borrowBook(UserBook userBook);

    boolean returnBook(UserBook userBook, int returnedQuantity);

    List<UserBook> findAllBorrowedBooks(int userId);

    UserBook findByBookIdAndUserId(int bookId, int userId);
}
