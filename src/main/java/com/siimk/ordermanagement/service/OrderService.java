package com.siimk.ordermanagement.service;


import com.siimk.ordermanagement.dto.OrderLineRequest;
import com.siimk.ordermanagement.dto.OrderRequest;
import com.siimk.ordermanagement.model.Customer;
import com.siimk.ordermanagement.model.Order;
import com.siimk.ordermanagement.model.OrderLine;
import com.siimk.ordermanagement.model.Product;
import com.siimk.ordermanagement.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final ProductService productService;
    public OrderService(OrderRepository orderRepository, CustomerService customerService, ProductService productService){
        this.orderRepository = orderRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    public String createOrder(OrderRequest orderRequest){

        Order newOrder = new Order();
        newOrder.setSubmissionDate(LocalDate.now());
        Customer customer = customerService.getCustomerById(orderRequest.getCustomerId());
        newOrder.setCustomer(customer);

        List<OrderLine> orderLines = new ArrayList<>();
        for (OrderLineRequest lineRequest : orderRequest.getOrderLines()) {
            Long productId = lineRequest.getProductId();
            Product product = productService.getProductById(productId);

            if (product == null) {
                // todo Handle the case where the product doesn't exist
                // todo exception, return an error message
            } else {
                OrderLine orderLine = new OrderLine(lineRequest.getQuantity(), product);
                orderLines.add(orderLine);
            }
        }

        newOrder.setOrderLines(orderLines);
        orderRepository.save(newOrder);

        return "Order created successfully";
    }
    public List<Order> getOrdersByDate(LocalDate submissionDate) {
        return orderRepository.findBySubmissionDate(submissionDate);
    }
}
