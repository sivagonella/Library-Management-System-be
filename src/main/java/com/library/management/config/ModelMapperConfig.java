package com.library.management.config;

import com.library.management.domain.Author;
import com.library.management.domain.LibraryBook;
import com.library.management.domain.LibraryBookDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper =  new ModelMapper();
        return modelMapper;

    }
}
