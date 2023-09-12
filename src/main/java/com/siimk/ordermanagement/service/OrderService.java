package com.siimk.ordermanagement.service;


import com.siimk.ordermanagement.model.Order;
import com.siimk.ordermanagement.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public String createOrder(Order order){

        Order newOrder = new Order();
        newOrder.setSubmissionDate(newOrder.getSubmissionDate());
        newOrder.setCustomer(order.getCustomer());
        newOrder.setOrderLines(newOrder.getOrderLines());
        orderRepository.save(newOrder);
        return "Order created successfully";
    }
    public List<Order> getOrdersByDate(LocalDate submissionDate) {
        return orderRepository.findBySubmissionDate(submissionDate);
    }
}
