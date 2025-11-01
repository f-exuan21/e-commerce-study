package kr.hhplus.be.server.ecommerce.adapter.out.jpa.entity;


import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "shipping_address", nullable = false, length=300)
    private String shippingAddress;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @Column(name = "order_date", nullable = false)
    private Instant orderDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items = new ArrayList<>();

    protected OrderEntity() {
    }

    public OrderEntity(Long userId, String shippingAddress, Long totalPrice, Instant orderDate) {
        this.userId = userId;
        this.shippingAddress = shippingAddress;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void addItem(OrderItemEntity item) {
        item.setOrder(this);
        items.add(item);
    }
}
