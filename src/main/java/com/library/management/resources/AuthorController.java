package com.library.management.resources;

import com.library.management.domain.entity.Author;
import com.library.management.domain.dto.AuthorDTO;
import com.library.management.services.AuthorService;
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
public class AuthorController {
    private AuthorService authorService;

    private ModelMapper modelMapper;

    @Autowired
    public AuthorController(AuthorService authorService, ModelMapper modelMapper) {
        this.authorService = authorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(path = "/authors")
    public ResponseEntity<AuthorDTO> addAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        AuthorDTO responseBody = modelMapper.map(authorService.addAuthor(author), AuthorDTO.class);
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @GetMapping(path = "/authors")
    public ResponseEntity<List<AuthorDTO>> findAllAuthors() {
        List<AuthorDTO> authorDTOS = authorService.findAll().stream().map((author) -> modelMapper.map(author, AuthorDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(authorDTOS, HttpStatus.OK);
    }
}
