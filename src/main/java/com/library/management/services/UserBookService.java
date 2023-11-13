package com.library.management.services;

import com.library.management.domain.entity.UserBook;

import java.util.List;
import java.util.UUID;

public interface UserBookService {
    boolean borrowBook(UserBook userBook);

    boolean returnBook(UserBook userBook, int returnedQuantity);

    List<UserBook> findAllBorrowedBooks(int userId);

    UserBook findByTransactionUUIDAndBookIdAndUserId(UUID transactionUUID, int bookId, int userId);
}
