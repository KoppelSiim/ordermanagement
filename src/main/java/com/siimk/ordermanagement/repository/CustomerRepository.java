package com.siimk.ordermanagement.repository;

import com.siimk.ordermanagement.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}

