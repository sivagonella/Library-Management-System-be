package com.library.management.repositories;

import com.library.management.domain.entity.UserBook;
import com.library.management.domain.entity.enums.BorrowedStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserBookRepository extends CrudRepository<UserBook, Integer> {

    List<UserBook> findByUserId(Integer userId);

    UserBook findByBookIdAndUserId(Integer bookId, Integer userId);

    List<UserBook> findByUserIdAndBorrowedStatus(Integer userId, BorrowedStatus borrowedStatus);

    UserBook findByBookIdAndUserIdAndBorrowedStatusIn(Integer bookId, Integer userId, List<BorrowedStatus> statuses);
}
