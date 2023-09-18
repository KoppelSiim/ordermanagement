package com.siimk.ordermanagement.service;

import com.siimk.ordermanagement.model.Product;
import com.siimk.ordermanagement.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String createProduct(Product product) {
        Product newProduct = new Product();
        newProduct.setName(product.getName());
        newProduct.setSkuCode(product.getSkuCode());
        newProduct.setUnitPrice(product.getUnitPrice());
        productRepository.save(newProduct);
        return "Product created successfully";
    }

    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + productId));
    }
}
