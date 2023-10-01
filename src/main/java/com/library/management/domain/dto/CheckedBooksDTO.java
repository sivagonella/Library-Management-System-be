package com.library.management.domain.dto;

import com.library.management.domain.entity.LibraryBook;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class CheckedBooksDTO implements Serializable {

    private List<CheckedBookDTO> checkedBookDTOs;

    public CheckedBooksDTO() {
        checkedBookDTOs = new ArrayList<>();
    }

}
