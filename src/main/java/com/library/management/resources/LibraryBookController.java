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

    @GetMapping(path = "/books")
    public @ResponseBody List<LibraryBooksDTO> findAllBooks() {
//        List<LibraryBookDTO> libraryBookDTOS = modelMapper.map(libraryBookService.getBooks(), new TypeToken<List<LibraryBookDTO>>() {
//        }.getType());
        List<LibraryBooksDTO> libraryBookDTOS = libraryBookService.getBooks().stream().map((book) -> modelMapper.map(book, LibraryBooksDTO.class)).collect(Collectors.toList());
        return libraryBookDTOS;
    }

    @PostMapping(path = "/books")
    public @ResponseBody LibraryBookDTO addBook(@RequestBody LibraryBookDTO bookDTO) {
        LibraryBook libraryBook = modelMapper.map(bookDTO, LibraryBook.class);
        libraryBook.setAuthors(authorService.findAllByIds(bookDTO.getAuthorIds()));
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

    @PostMapping(path = "/users/email={email}")
    public @ResponseBody UserDTO findUser(@PathVariable String email, @RequestBody LoginUserDTO loginUserDTO) {
        User user = userAuthenticationService.getUserByEmailID(email);
        if(user != null)
            return modelMapper.map(user, UserDTO.class);
        return null;
    }

    @PostMapping(path = "/users")
    public @ResponseBody UserDTO addUser(UserDTO userDTO) {
        return modelMapper.map(userAuthenticationService.addUser(modelMapper.map(userDTO, User.class)), UserDTO.class);
    }

}
