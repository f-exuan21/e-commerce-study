package kr.hhplus.be.server.ecommerce.domain.policy;

import kr.hhplus.be.server.ecommerce.domain.model.money.Money;
import kr.hhplus.be.server.ecommerce.domain.model.order.OrderItem;

import java.util.List;

public interface PricingPolicy {
    Money calculateTotal(List<OrderItem> orderItems);
}
