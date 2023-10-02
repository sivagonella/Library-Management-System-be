package com.library.management.services.impl;

import com.library.management.domain.entity.TransactionOrderDetail;
import com.library.management.domain.entity.UserBook;
import com.library.management.domain.entity.enums.BorrowedStatus;
import com.library.management.repositories.TransactionOrderDetailRepository;
import com.library.management.repositories.UserBookRepository;
import com.library.management.services.LibraryBookService;
import com.library.management.services.UserBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserBookServiceImpl implements UserBookService {

    private UserBookRepository userBookRepository;

    private TransactionOrderDetailRepository transactionOrderDetailRepository;

    private LibraryBookService libraryBookService;

    @Autowired
    public UserBookServiceImpl(UserBookRepository userBookRepository, LibraryBookService libraryBookService, TransactionOrderDetailRepository transactionOrderDetailRepository) {
        this.userBookRepository = userBookRepository;
        this.libraryBookService = libraryBookService;
        this.transactionOrderDetailRepository = transactionOrderDetailRepository;
    }

    @Override
    public boolean borrowBook(UserBook userBook) {
        UserBook userBook1 = userBookRepository.save(userBook);
        boolean isLibraryBookUpdated = libraryBookService.updateBookCount(userBook.getBookId(), userBook.getBorrowedQuantity(), userBook.getBorrowedStatus());
        return userBook1 != null && isLibraryBookUpdated;
    }

    @Override
    public boolean returnBook(UserBook userBook, Integer returnedQuantity) {
        if(returnedQuantity == 0) {
            return false;
        }
        if(userBook.getBorrowedQuantity() == returnedQuantity) {
            userBook.setBorrowedStatus(BorrowedStatus.RETURNED);
            libraryBookService.updateBookCount(userBook.getBookId(), returnedQuantity, BorrowedStatus.RETURNED);
            TransactionOrderDetail transactionOrderDetail = new TransactionOrderDetail(userBook, returnedQuantity, new Date());
            transactionOrderDetailRepository.save(transactionOrderDetail);
            return true;
        }
        if(userBook.getBorrowedQuantity() > returnedQuantity) {
            System.out.println("Transaction ID: " + userBook.getTransactionUUID());
            System.out.println("This is the already returned value: " + transactionOrderDetailRepository.findNumberOfReturnedBooks(userBook.getTransactionUUID()));
            if(transactionOrderDetailRepository.findNumberOfReturnedBooks(userBook.getTransactionUUID()) + returnedQuantity == userBook.getBorrowedQuantity()) {
                userBook.setBorrowedStatus(BorrowedStatus.RETURNED);
            }
            else {
                userBook.setBorrowedStatus(BorrowedStatus.PARTIALLY_RETURNED);
            }
            libraryBookService.updateBookCount(userBook.getBookId(), returnedQuantity, BorrowedStatus.RETURNED);
            TransactionOrderDetail transactionOrderDetail = new TransactionOrderDetail(userBook, returnedQuantity, new Date());
            transactionOrderDetailRepository.save(transactionOrderDetail);
            return true;
        }
        return false;
    }

    @Override
    public List<UserBook> findAllBorrowedBooks(Integer userId) {
        List<UserBook> borrowedBooks = new ArrayList<>();
        borrowedBooks.addAll(userBookRepository.findByUserIdAndBorrowedStatus(userId, BorrowedStatus.BORROWED));
        List<UserBook> partiallyReturnedBooks = userBookRepository.findByUserIdAndBorrowedStatus(userId, BorrowedStatus.PARTIALLY_RETURNED);
        for(UserBook userBook : partiallyReturnedBooks) {
            List<TransactionOrderDetail> transactionOrderDetailList = transactionOrderDetailRepository.findByUserBook(userBook);
            int totalReturnedBooks = 0;
            for(TransactionOrderDetail transactionOrderDetail : transactionOrderDetailList) {
                totalReturnedBooks += transactionOrderDetail.getReturnedQuantity();
            }
            userBook.setBorrowedQuantity(userBook.getBorrowedQuantity() - totalReturnedBooks);
            borrowedBooks.add(userBook);
        }
        return borrowedBooks;
    }

    @Override
    public UserBook findByBookIdAndUserId(Integer bookId, Integer userId) {
        List<BorrowedStatus> statuses = new ArrayList<>();
        statuses.add(BorrowedStatus.PARTIALLY_RETURNED);
        statuses.add(BorrowedStatus.BORROWED);
        return userBookRepository.findByBookIdAndUserIdAndBorrowedStatusIn(bookId, userId, statuses);
    }
}
