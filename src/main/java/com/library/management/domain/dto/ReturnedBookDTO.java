package com.library.management.domain.dto;

import lombok.Data;

@Data
public class ReturnedBookDTO {

    private Integer bookId;

    private Integer returnedQuantity;
}
