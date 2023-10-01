package com.library.management.services.impl;

import com.library.management.domain.entity.Author;
import com.library.management.repositories.AuthorRepository;
import com.library.management.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public List<Author> findAllByIds(List<Integer> authorIds) {
        return (List<Author>) authorRepository.findAllById(authorIds);
    }
}
