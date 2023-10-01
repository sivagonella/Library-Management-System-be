package com.library.management.services;

import com.library.management.domain.entity.UserBook;

import java.util.List;

public interface UserBookService {
    boolean borrowBook(UserBook userBook);

    List<UserBook> findAllBorrowedBooks(Integer userId);
}
