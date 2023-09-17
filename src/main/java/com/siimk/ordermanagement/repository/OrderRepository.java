package com.siimk.ordermanagement.repository;

import com.siimk.ordermanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findBySubmissionDate(LocalDate submissionDate);

    @Query("SELECT o FROM Order o JOIN o.orderLines ol WHERE ol.product.id = ?1")
    List<Order> findOrdersByProductId(Long id);

    @Query("SELECT o FROM Order o JOIN o.customer c WHERE c.id = ?1")
    List<Order> findOrdersByCustomerId(Long id);
}
