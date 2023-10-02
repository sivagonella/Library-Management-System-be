package com.library.management.services;

import com.library.management.domain.entity.UserBook;

import java.util.List;

public interface UserBookService {
    boolean borrowBook(UserBook userBook);

    boolean returnBook(UserBook userBook, Integer returnedQuantity);

    List<UserBook> findAllBorrowedBooks(Integer userId);

    UserBook findByBookIdAndUserId(Integer bookId, Integer userId);
}
