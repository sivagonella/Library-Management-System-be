package com.library.management.domain.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ReturnedBookDTO {
    private UUID transactionUUID;

    private int bookId;

    private int returnedQuantity;
}
