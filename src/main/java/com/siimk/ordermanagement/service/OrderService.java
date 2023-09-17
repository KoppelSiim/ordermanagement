package com.siimk.ordermanagement.service;


import com.siimk.ordermanagement.dto.OrderLineRequest;
import com.siimk.ordermanagement.dto.OrderRequest;
import com.siimk.ordermanagement.model.Customer;
import com.siimk.ordermanagement.model.Order;
import com.siimk.ordermanagement.model.OrderLine;
import com.siimk.ordermanagement.model.Product;
import com.siimk.ordermanagement.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public void updateOrderLineQuantity(Long orderId, Long orderLineId, int newQuantity) {

        Optional<Order> orderToUpdate = orderRepository.findById(orderId);
        if (orderToUpdate.isPresent()) {
            Order order = orderToUpdate.get();

            for (OrderLine orderLine : order.getOrderLines()) {
                if (orderLine.getId().equals(orderLineId)) {
                    orderLine.setQuantity(newQuantity);
                    break;
                }
            }

            orderRepository.save(order);
        } else {
            throw new EntityNotFoundException("Order not found with ID: " + orderId);
        }
    }



    public List<Order> getOrdersByDate(LocalDate submissionDate) {
        return orderRepository.findBySubmissionDate(submissionDate);
    }
    public List<Order> searchOrdersByProduct(Long id){
        return orderRepository.findOrdersByProductId(id);
    }

    public List<Order> searchOrdersByCustomer(Long id){
        return orderRepository.findOrdersByCustomerId(id);
    }

}
