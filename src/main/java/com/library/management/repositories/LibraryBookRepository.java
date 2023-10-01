package com.library.management.repositories;

import com.library.management.domain.entity.LibraryBook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibraryBookRepository extends CrudRepository<LibraryBook, Integer> {
    List<LibraryBook> findAll();

    List<LibraryBook> findByQuantityGreaterThan(int number_of_books);
}
