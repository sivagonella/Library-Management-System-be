package com.library.management.services;

import com.library.management.domain.Author;

import java.util.List;

public interface AuthorService {
    public Author addAuthor(Author author);

    public List<Author> findAll();
}
