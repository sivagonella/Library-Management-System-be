package com.library.management.domain.dto;

import lombok.Data;

@Data
public class ReturnedBookDTO {

    private int bookId;

    private int returnedQuantity;
}
