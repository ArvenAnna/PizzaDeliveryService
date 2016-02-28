
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anna.pizzadeliveryservice.dto;

import anna.pizzadeliveryservice.domain.Order;

/**
 *
 * @author Anna
 */
public class OrderDto {
    
    private Long orderId;
    private Order.Status status;

    public OrderDto() {
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Order.Status getStatus() {
        return status;
    }

    public void setStatus(Order.Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderDto{" + "orderId=" + orderId + ", status=" + status + '}';
    }
    
    
}
