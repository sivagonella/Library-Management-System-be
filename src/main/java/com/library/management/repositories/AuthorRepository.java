package com.library.management.repositories;

import com.library.management.domain.Author;
import com.library.management.domain.LibraryBook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Integer> {

    List<Author> findAll();
    List<Author> findById(LibraryBook libraryBook);
}
