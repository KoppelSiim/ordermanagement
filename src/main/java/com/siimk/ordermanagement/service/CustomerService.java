package com.siimk.ordermanagement.service;

import com.siimk.ordermanagement.model.Customer;
import com.siimk.ordermanagement.repository.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String createCustomer(Customer customer){
        Customer newCustomer = new Customer();
        newCustomer.setRegistrationCode(customer.getRegistrationCode());
        newCustomer.setFullName(customer.getFullName());
        newCustomer.setEmail(customer.getEmail());
        newCustomer.setTelephone(customer.getTelephone());
        customerRepository.save(newCustomer);

        return "Customer created successfully";
    }

    public Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found with ID: " + customerId));
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


}
