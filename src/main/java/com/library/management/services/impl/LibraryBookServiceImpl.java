package com.library.management.services.impl;

import com.library.management.domain.dto.LibraryBooksDTO;
import com.library.management.domain.entity.LibraryBook;
import com.library.management.domain.entity.enums.BorrowedStatus;
import com.library.management.repositories.LibraryBookRepository;
import com.library.management.services.LibraryBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryBookServiceImpl implements LibraryBookService {

    @Autowired
    private LibraryBookRepository libraryBookRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LibraryBooksDTO getBooks(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<LibraryBook> page = libraryBookRepository.findAll(pageable);
        List<LibraryBook> libraryBooks = page.getContent();
        LibraryBooksDTO libraryBooksDTO = new LibraryBooksDTO();
        libraryBooksDTO.setLibraryBooks(libraryBooks);
        libraryBooksDTO.setPageNo(page.getNumber());
        libraryBooksDTO.setPageSize(page.getSize());
        libraryBooksDTO.setTotalPages(page.getTotalPages());
        libraryBooksDTO.setTotalElements(page.getTotalElements());
        libraryBooksDTO.setLast(page.isLast());
        return libraryBooksDTO;
    }

    @Override
    public LibraryBook addBook(LibraryBook libraryBook) {
        return libraryBookRepository.save(libraryBook);
    }

    @Override
    public boolean updateBookCount(int bookId, int quantity, BorrowedStatus borrowedStatus) {
        Optional<LibraryBook> optionalLibraryBook = libraryBookRepository.findById(bookId);
        if(optionalLibraryBook.isPresent()) {
            LibraryBook libraryBook = optionalLibraryBook.get();

            if(borrowedStatus == BorrowedStatus.BORROWED) {
                libraryBook.setQuantity(libraryBook.getQuantity() - quantity);
            }
            else if(borrowedStatus == BorrowedStatus.RETURNED) {
                libraryBook.setQuantity(libraryBook.getQuantity() + quantity);
            }
            LibraryBook updatedLibraryBook = libraryBookRepository.save(libraryBook);
            return updatedLibraryBook != null;
        }
        return false;
    }

    @Override
    public LibraryBook getBookById(int bookId) {
        Optional<LibraryBook> optionalLibraryBook = libraryBookRepository.findById(bookId);
        if(optionalLibraryBook.isPresent()) {
            return optionalLibraryBook.get();
        }
        return null;
    }

}
