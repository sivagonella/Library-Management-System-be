package com.library.management.resources;

import com.library.management.domain.*;
import com.library.management.services.AuthorService;
import com.library.management.services.LibraryBookService;
import com.library.management.services.UserAuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
@RequestMapping(path = "/demo")
public class LibraryBookController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LibraryBookService libraryBookService;
    @Autowired
    private UserAuthenticationService userAuthenticationService;
    @Autowired
    private AuthorService authorService;

    @GetMapping(path = "/listBooks")
    public @ResponseBody List<LibraryBookDTO> findAllBooks() {
//        List<LibraryBookDTO> libraryBookDTOS = modelMapper.map(libraryBookService.getBooks(), new TypeToken<List<LibraryBookDTO>>() {
//        }.getType());
        List<LibraryBookDTO> libraryBookDTOS = libraryBookService.getBooks().stream().map((book) -> modelMapper.map(book, LibraryBookDTO.class)).collect(Collectors.toList());
        return libraryBookDTOS;
    }

    @PostMapping(path = "/addBook")
    public @ResponseBody LibraryBookDTO addBook(@RequestBody LibraryBookDTO bookDTO) {
        LibraryBook libraryBook = modelMapper.map(bookDTO, LibraryBook.class);
        return modelMapper.map(libraryBookService.addBook(libraryBook), LibraryBookDTO.class);
    }

    @PostMapping(path = "/authors")
    public @ResponseBody AuthorDTO addAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        return modelMapper.map(authorService.addAuthor(author), AuthorDTO.class);
    }

    @GetMapping(path = "/authors")
    public @ResponseBody List<AuthorDTO> findAllAuthors() {
        List<AuthorDTO> authorDTOS = authorService.findAll().stream().map((author) -> modelMapper.map(author, AuthorDTO.class)).collect(Collectors.toList());
        return authorDTOS;
    }

    @PostMapping(path = "/users")
    public @ResponseBody NewUserDTO findUser(@RequestBody LoginUserDTO loginUserDTO) {
        return modelMapper.map(userAuthenticationService.getUserByEmailID(loginUserDTO.getEmail()), NewUserDTO.class);
    }

}
