package com.library.management.domain.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
@Data
public class LibraryBookDTO implements Serializable {

    private Integer id;

    private String name;

    private List<Integer> authorIds;

    private Integer quantity;
}