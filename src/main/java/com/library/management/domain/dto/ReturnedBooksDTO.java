package com.library.management.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnedBooksDTO implements Serializable {
    private List<ReturnedBookDTO> returnedBookDTOList;
}
