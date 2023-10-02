package com.library.management.repositories;

import com.library.management.domain.entity.TransactionOrderDetail;
import com.library.management.domain.entity.UserBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TransactionOrderDetailRepository extends CrudRepository<TransactionOrderDetail, Integer> {

    List<TransactionOrderDetail> findByUserBook(UserBook userBook);

    @Query(value = "select sum(o.returned_quantity) from transaction_order_detail o where o.transaction_id = :transactionId", nativeQuery = true)
    Integer findNumberOfReturnedBooks(@Param("transactionId") UUID transactionId);
}
