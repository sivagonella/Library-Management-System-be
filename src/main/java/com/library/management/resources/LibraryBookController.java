package com.library.management.resources;

import com.library.management.domain.LibraryBook;
import com.library.management.domain.LibraryBookDTO;
import com.library.management.domain.LoginUserDTO;
import com.library.management.domain.UserDTO;
import com.library.management.services.LibraryBookService;
import com.library.management.services.UserAuthenticationService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
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

    @GetMapping(path = "/listBooks")
    public @ResponseBody List<LibraryBookDTO> listAllBooks() {
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

    @PostMapping(path = "/getUser")
    public @ResponseBody UserDTO getUser(@RequestBody LoginUserDTO loginUserDTO) {
        return modelMapper.map(userAuthenticationService.getUserByEmailID(loginUserDTO.getEmail()), UserDTO.class);
    }
}
