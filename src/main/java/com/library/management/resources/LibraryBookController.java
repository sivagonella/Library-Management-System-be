package com.library.management.resources;

import com.library.management.domain.dto.LibraryBookDTO;
import com.library.management.domain.dto.LibraryBooksDTO;
import com.library.management.domain.dto.UserBookDTO;
import com.library.management.domain.entity.LibraryBook;
import com.library.management.domain.entity.UserBook;
import com.library.management.repositories.UserBookRepository;
import com.library.management.services.AuthorService;
import com.library.management.services.LibraryBookService;
import com.library.management.services.UserAuthenticationService;
import com.library.management.services.UserBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
public class LibraryBookController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LibraryBookService libraryBookService;
    @Autowired
    private UserAuthenticationService userAuthenticationService;
    @Autowired
    private AuthorService authorService;

    @Autowired
    private UserBookService userBookService;

    @GetMapping(path = "/books")
    public ResponseEntity<List<LibraryBooksDTO>> findAllBooks() {
//        List<LibraryBookDTO> libraryBookDTOS = modelMapper.map(libraryBookService.getBooks(), new TypeToken<List<LibraryBookDTO>>() {
//        }.getType());
        List<LibraryBooksDTO> libraryBookDTOS = libraryBookService.getBooks().stream().map((book) -> modelMapper.map(book, LibraryBooksDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(libraryBookDTOS, HttpStatus.OK);
    }

    @PostMapping(path = "/books")
    public ResponseEntity<LibraryBookDTO> addBook(@RequestBody LibraryBookDTO bookDTO) {
        LibraryBook libraryBook = modelMapper.map(bookDTO, LibraryBook.class);
        libraryBook.setAuthors(authorService.findAllByIds(bookDTO.getAuthorIds()));
        LibraryBookDTO responseBody = modelMapper.map(libraryBookService.addBook(libraryBook), LibraryBookDTO.class);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}
