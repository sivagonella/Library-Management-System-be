package com.library.management.domain.dto;

import com.library.management.domain.entity.LibraryBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckedBookDTO {
    private LibraryBook libraryBook;

    private Integer quantity;

    private Date borrowedDate;

    private Date returnDate;
}
