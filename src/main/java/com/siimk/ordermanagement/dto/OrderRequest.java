package com.siimk.ordermanagement.dto;

import java.util.List;

public class OrderRequest {
    private Long customerId;
    private List<OrderLineRequest> orderLines;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderLineRequest> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLineRequest> orderLines) {
        this.orderLines = orderLines;
    }
}
