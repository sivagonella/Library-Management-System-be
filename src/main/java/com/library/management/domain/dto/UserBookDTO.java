package com.library.management.domain.dto;

import com.library.management.domain.entity.enums.BorrowedStatus;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class UserBookDTO implements Serializable {

    private int userId;

    private List<Integer> bookIds;

    private List<Integer> borrowedQuantities;

    private BorrowedStatus borrowedStatus;

}
