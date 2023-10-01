package com.library.management.services.impl;

import com.library.management.domain.entity.LibraryBook;
import com.library.management.domain.entity.enums.BorrowedStatus;
import com.library.management.repositories.LibraryBookRepository;
import com.library.management.services.LibraryBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryBookServiceImpl implements LibraryBookService {

    @Autowired
    private LibraryBookRepository libraryBookRepository;

    @Override
    public List<LibraryBook> getBooks() {
        return libraryBookRepository.findAll();
    }

    @Override
    public LibraryBook addBook(LibraryBook libraryBook) {
        return libraryBookRepository.save(libraryBook);
    }

    @Override
    public boolean updateBookCount(Integer bookId, Integer quantity, BorrowedStatus borrowedStatus) {
        Optional<LibraryBook> optionalLibraryBook = libraryBookRepository.findById(bookId);
        if(optionalLibraryBook.isPresent()) {
            LibraryBook libraryBook = optionalLibraryBook.get();

            if(borrowedStatus == BorrowedStatus.BORROWED) {
                libraryBook.setQuantity(libraryBook.getQuantity() - quantity);
            }
            else if(borrowedStatus == BorrowedStatus.RETURNED) {
                libraryBook.setQuantity(libraryBook.getQuantity() + quantity);
            }
            LibraryBook updatedLibraryBook = libraryBookRepository.save(libraryBook);
            return updatedLibraryBook != null;
        }
        return false;
    }

    @Override
    public LibraryBook getBookById(Integer bookId) {
        Optional<LibraryBook> optionalLibraryBook = libraryBookRepository.findById(bookId);
        if(optionalLibraryBook.isPresent()) {
            return optionalLibraryBook.get();
        }
        return null;
    }

}
