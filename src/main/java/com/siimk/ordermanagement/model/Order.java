package com.siimk.ordermanagement.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Table(name = "customer_order")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_order_id")
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "submission_date")
    private Date submissionDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "order_lines")
    private List<OrderLine> orderLines;

    public Order() {
    }

    public Order(Date submissionDate, Customer customer, List<OrderLine> orderLines) {
        this.submissionDate = submissionDate;
        this.customer = customer;
        this.orderLines = orderLines;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
        this.submissionDate = submissionDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

}
