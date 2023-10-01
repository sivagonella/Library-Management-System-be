package com.library.management.services.impl;

import com.library.management.domain.entity.UserBook;
import com.library.management.repositories.UserBookRepository;
import com.library.management.services.LibraryBookService;
import com.library.management.services.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserBookServiceImpl implements UserBookService {

    private UserBookRepository userBookRepository;

    private LibraryBookService libraryBookService;

    @Autowired
    public UserBookServiceImpl(UserBookRepository userBookRepository, LibraryBookService libraryBookService) {
        this.userBookRepository = userBookRepository;
        this.libraryBookService = libraryBookService;
    }

    @Override
    public boolean borrowBook(UserBook userBook) {
        UserBook userBook1 = userBookRepository.save(userBook);
        boolean isLibraryBookUpdated = libraryBookService.updateBookCount(userBook.getBookId(), userBook.getBorrowedQuantity(), userBook.getBorrowedStatus());
        if(userBook1 != null && isLibraryBookUpdated)
            return true;
        return false;
    }

    @Override
    public List<UserBook> findAllBorrowedBooks(Integer userId) {
        return userBookRepository.findByUserId(userId);
    }
}
