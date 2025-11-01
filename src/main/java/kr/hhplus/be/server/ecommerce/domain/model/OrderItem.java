package kr.hhplus.be.server.ecommerce.domain.model;

public record OrderItem(Long productId, Integer quantity, Money unitPrice) {

    public OrderItem(Long productId, Integer quantity) {
        this(productId, quantity, Money.of(0L)); // 또는 기본 단가 전략에 맞게 변경
    }


    public Money lineTotal() {
        return Money.of(unitPrice.toLong() * quantity);
    }

    public OrderItem withUnitPrice(Money unitPrice) {
        return new OrderItem(productId, quantity, unitPrice);
    }

}
