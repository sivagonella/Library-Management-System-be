package com.library.management.repositories;

import com.library.management.domain.entity.LibraryBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface LibraryBookRepository extends JpaRepository<LibraryBook, Integer> {
    Page<LibraryBook> findAll(Pageable pageable);

    List<LibraryBook> findByQuantityGreaterThan(int number_of_books);
}
