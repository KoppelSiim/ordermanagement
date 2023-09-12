package com.siimk.ordermanagement.repository;

import com.siimk.ordermanagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
