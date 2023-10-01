package com.library.management.domain.dto;

import com.library.management.domain.entity.LibraryBook;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CheckedBooksDTO implements Serializable {

    private List<LibraryBook> borrowedBooks;

    private List<Integer> borrowedQuantities;

    private List<Date> borrowedDates;

    public CheckedBooksDTO(List<LibraryBook> borrowedBooks, List<Integer> borrowedQuantities, List<Date> borrowedDates) {
        this.borrowedBooks = borrowedBooks;
        this.borrowedQuantities = borrowedQuantities;
        this.borrowedDates = borrowedDates;
    }
}
