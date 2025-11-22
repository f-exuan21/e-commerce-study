package kr.hhplus.be.server.ecommerce.domain.policy.impl;

import kr.hhplus.be.server.ecommerce.domain.model.money.Money;
import kr.hhplus.be.server.ecommerce.domain.model.order.OrderItem;
import kr.hhplus.be.server.ecommerce.domain.policy.PricingPolicy;

import java.util.List;

public class DefaultPricingPolicy implements PricingPolicy {

    @Override
    public Money calculateTotal(List<OrderItem> orderItems) {
        Money sum = Money.of(0);

        for (OrderItem item : orderItems) {
            sum = sum.plus(item.lineTotal());
        }

        return sum;
    }
}
