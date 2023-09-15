package com.siimk.ordermanagement.controller;

import com.siimk.ordermanagement.dto.OrderRequest;
import com.siimk.ordermanagement.model.Customer;
import com.siimk.ordermanagement.model.Order;
import com.siimk.ordermanagement.model.Product;
import com.siimk.ordermanagement.service.CustomerService;
import com.siimk.ordermanagement.service.OrderService;
import com.siimk.ordermanagement.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    private final CustomerService customerService;
    private final ProductService productService;
    private final OrderService orderService;
    public MainController(CustomerService customerService, ProductService productService, OrderService orderService){
        this.customerService = customerService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @PostMapping("/createcustomer")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        String message = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/createproduct")
    public ResponseEntity<String> createCustomer(@RequestBody Product product){
        String message = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PostMapping("/createorder")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            String response = orderService.createOrder(orderRequest);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            // Handle the case where the customer or product is not found
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            // Handle other exceptions, such as database errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order");
        }
    }
}
