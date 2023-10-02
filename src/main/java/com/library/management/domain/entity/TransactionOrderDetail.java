package com.library.management.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transaction_order_detail")
@Data
@NoArgsConstructor
public class TransactionOrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private UUID orderId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transaction_id")
    private UserBook userBook;

    @Column(name = "returned_quantity")
    private Integer returnedQuantity;

    @Column(name = "returned_date")
    private Date returnedDate;

    public TransactionOrderDetail(UserBook userBook, Integer returnedQuantity, Date date) {
        this.userBook = userBook;
        this.returnedDate = date;
        this.returnedQuantity = returnedQuantity;
    }
}
