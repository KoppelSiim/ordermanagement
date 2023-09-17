package com.siimk.ordermanagement.controller;

import com.siimk.ordermanagement.dto.OrderRequest;
import com.siimk.ordermanagement.model.Customer;
import com.siimk.ordermanagement.model.Order;
import com.siimk.ordermanagement.model.Product;
import com.siimk.ordermanagement.service.CustomerService;
import com.siimk.ordermanagement.service.OrderService;
import com.siimk.ordermanagement.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/createCustomer")
    public ResponseEntity<String> createCustomer(@RequestBody Customer customer) {
        String message = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    @PostMapping("/createProduct")
    public ResponseEntity<String> createCustomer(@RequestBody Product product){
        String message = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }
    @PostMapping("/createOrder")
    public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
        try {
            String response = orderService.createOrder(orderRequest);
            return ResponseEntity.ok(response);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order");
        }
    }
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> customers = customerService.getAllCustomers();
            if (customers.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(customers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/searchOrder")
    public ResponseEntity<?> searchOrdersByDate
            (@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Order> orders = orderService.getOrdersByDate(date);
        if (orders.isEmpty()) {
            String message = "No orders on " + date.toString();
            Map<String, String> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.ok(response);
        }
        else{
        return ResponseEntity.ok(orders);
        }
    }
    @GetMapping("/ordersByProduct")
    public ResponseEntity<List<Order>> searchOrdersByProduct(@RequestParam("id") Long id) {
        List<Order> orders = orderService.searchOrdersByProduct(id);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }
    @GetMapping("/ordersByCustomer")
    public ResponseEntity<List<Order>> searchOrdersByCustomer(@RequestParam("id") Long id) {
        List<Order> orders = orderService.searchOrdersByCustomer(id);
        if (orders.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(orders);
    }

}
