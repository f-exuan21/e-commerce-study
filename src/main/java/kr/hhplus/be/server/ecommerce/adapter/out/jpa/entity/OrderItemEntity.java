package kr.hhplus.be.server.ecommerce.adapter.out.jpa.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.ecommerce.domain.model.Money;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false)
    private Long unitPrice;

    protected OrderItemEntity() {
    }

    public OrderItemEntity(Long productId, Integer quantity, Money unitPrice) {
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = unitPrice.toLong();
    }

    void setOrder(OrderEntity order) {
        this.order = order;
    }

}
