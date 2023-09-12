package com.siimk.ordermanagement.repository;

import com.siimk.ordermanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findBySubmissionDate(LocalDate submissionDate);
}
