package com.library.management.repositories;

import com.library.management.domain.entity.UserBook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserBookRepository extends CrudRepository<UserBook, Integer> {

    List<UserBook> findByUserId(Integer userId);
}
