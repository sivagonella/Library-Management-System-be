package com.library.management.resources;

import com.library.management.domain.entity.Author;
import com.library.management.domain.dto.AuthorDTO;
import com.library.management.services.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public @ResponseBody AuthorDTO addAuthor(@RequestBody AuthorDTO authorDTO) {
        Author author = modelMapper.map(authorDTO, Author.class);
        return modelMapper.map(authorService.addAuthor(author), AuthorDTO.class);
    }

    @GetMapping(path = "/authors")
    public @ResponseBody List<AuthorDTO> findAllAuthors() {
        List<AuthorDTO> authorDTOS = authorService.findAll().stream().map((author) -> modelMapper.map(author, AuthorDTO.class)).collect(Collectors.toList());
        return authorDTOS;
    }
}
