package kr.hhplus.be.server.ecommerce.domain.policy;

import kr.hhplus.be.server.ecommerce.domain.model.Money;
import kr.hhplus.be.server.ecommerce.domain.model.OrderItem;

import java.util.List;

public interface PricingPolicy {
    Money calculateTotal(List<OrderItem> orderItems);
}
