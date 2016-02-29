package anna.pizzadeliveryservice.dto;

import anna.pizzadeliveryservice.domain.Order;

/**
 * DTO object for order status changing
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
