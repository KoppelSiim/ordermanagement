package com.siimk.ordermanagement.repository;

import com.siimk.ordermanagement.model.Customer;
import com.siimk.ordermanagement.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {


}

