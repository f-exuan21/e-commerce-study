package kr.hhplus.be.server.ecommerce.domain.model;

import java.time.Instant;
import java.util.List;

public class Order {

    private final Long userId;
    private final List<OrderItem> orderItems;
    private final String shippingAddress;
    private final Money totalPrice;
    private final Instant orderDate;

    private Order(Long userId, List<OrderItem> orderItems, String shippingAddress, Money totalPrice, Instant orderDate) {
        this.userId = userId;
        this.orderItems = orderItems;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public static Order of(Long userId, List<OrderItem> orderItems, String shippingAddress, Money totalPrice, Instant orderDate) {
        return new Order(userId, orderItems, shippingAddress, totalPrice, orderDate);
    }


    public Instant orderDate() {
        return orderDate;
    }

    public Long getUserId() {
        return userId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public Money getTotalPrice() {
        return totalPrice;
    }

    public Instant getOrderDate() {
        return orderDate;
    }
}
