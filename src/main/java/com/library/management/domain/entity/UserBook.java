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
    private int userId;

    @Column(name = "book_id", nullable = false)
    private int bookId;

    @Column(name = "borrowed_quantity", nullable = false)
    private int borrowedQuantity;

    @Enumerated(EnumType.STRING)
    private BorrowedStatus borrowedStatus;

    @Column(name = "date", nullable = false)
    private Date borrowedDate;

    public UserBook(Integer userId, int bookId, int borrowedQuantity, BorrowedStatus borrowedStatus, Date borrowedDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowedQuantity = borrowedQuantity;
        this.borrowedStatus = borrowedStatus;
        this.borrowedDate = borrowedDate;
    }
}
