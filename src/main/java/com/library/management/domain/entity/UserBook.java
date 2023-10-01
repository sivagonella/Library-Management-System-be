package com.library.management.domain.entity;

import com.library.management.domain.entity.enums.BorrowedStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "user_book")
@Data
@NoArgsConstructor
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "transaction_id", nullable = false)
    private UUID transactionUUID;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "book_id", nullable = false)
    private Integer bookId;

    @Column(name = "borrowed_quantity", nullable = false)
    private Integer borrowedQuantity;

    @Enumerated(EnumType.STRING)
    private BorrowedStatus borrowedStatus;

    @Column(name = "date", nullable = false)
    private Date date;


    public UserBook(Integer userId, int bookId, int borrowedQuantity, BorrowedStatus borrowedStatus, Date date) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowedQuantity = borrowedQuantity;
        this.borrowedStatus = borrowedStatus;
        this.date = date;
    }
}
