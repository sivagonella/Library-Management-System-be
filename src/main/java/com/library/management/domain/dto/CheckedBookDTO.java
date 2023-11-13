package com.library.management.domain.dto;

import com.library.management.domain.entity.LibraryBook;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckedBookDTO {
    private UUID transactionUUID;

    private LibraryBook libraryBook;

    private int quantity;

    private Date borrowedDate;

    private Date returnDate;
}
