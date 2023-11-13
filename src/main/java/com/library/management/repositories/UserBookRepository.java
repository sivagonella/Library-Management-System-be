package com.library.management.repositories;

import com.library.management.domain.entity.UserBook;
import com.library.management.domain.entity.enums.BorrowedStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface UserBookRepository extends CrudRepository<UserBook, Integer> {

    List<UserBook> findByUserId(int userId);

    UserBook findByBookIdAndUserId(int bookId, int userId);

    List<UserBook> findByUserIdAndBorrowedStatus(int userId, BorrowedStatus borrowedStatus);

    UserBook findByTransactionUUIDAndBookIdAndUserIdAndBorrowedStatusIn(UUID transactionUUID, int bookId, int userId, List<BorrowedStatus> statuses);
}
