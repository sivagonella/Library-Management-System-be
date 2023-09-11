package com.library.management.services;

import com.library.management.domain.LibraryBook;
import com.library.management.repositories.LibraryBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryBookServiceImpl implements LibraryBookService{

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

}
