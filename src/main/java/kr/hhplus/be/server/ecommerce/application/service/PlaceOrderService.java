package kr.hhplus.be.server.ecommerce.application.service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.ecommerce.application.port.dto.PlaceOrderCommand;
import kr.hhplus.be.server.ecommerce.application.port.dto.PlaceOrderResult;
import kr.hhplus.be.server.ecommerce.application.port.in.PlaceOrderUseCase;
import kr.hhplus.be.server.ecommerce.application.port.out.LoadProductPort;
import kr.hhplus.be.server.ecommerce.application.port.out.PaymentPort;
import kr.hhplus.be.server.ecommerce.application.port.out.SaveOrderPort;
import kr.hhplus.be.server.ecommerce.application.port.out.TakeOutProductPort;
import kr.hhplus.be.server.ecommerce.domain.exception.OutOfStockException;
import kr.hhplus.be.server.ecommerce.domain.model.Money;
import kr.hhplus.be.server.ecommerce.domain.model.Order;
import kr.hhplus.be.server.ecommerce.domain.model.OrderItem;
import kr.hhplus.be.server.ecommerce.domain.policy.PricingPolicy;

import java.time.Instant;

@Transactional
public class PlaceOrderService implements PlaceOrderUseCase {

    private final LoadProductPort loadProductPort;
    private final SaveOrderPort saveOrderPort;
    private final PricingPolicy pricingPolicy;
    private final TakeOutProductPort takeOutProductPort;
    private final PaymentPort paymentPort;

    public PlaceOrderService(LoadProductPort loadProductPort, SaveOrderPort saveOrderPort, PricingPolicy pricingPolicy, TakeOutProductPort takeOutProductPort, PaymentPort paymentPort) {
        this.loadProductPort = loadProductPort;
        this.saveOrderPort = saveOrderPort;
        this.pricingPolicy = pricingPolicy;
        this.takeOutProductPort = takeOutProductPort;
        this.paymentPort = paymentPort;
    }

    @Override
    public PlaceOrderResult placeOrder(PlaceOrderCommand cmd) {

        var items = cmd.orderItems().stream()
                .map(i -> new OrderItem(i.productId(), i.quantity()))
                .toList();

        var pricedItems = items.stream()
                .map(i -> i.withUnitPrice(loadProductPort.getUnitPrice(i.productId())))
                .toList();

        Money total = pricingPolicy.calculateTotal(pricedItems);

        for (var item : pricedItems) {
            int stock = loadProductPort.getStock(item.productId());
            if (stock < item.quantity()) {
                throw new OutOfStockException();
            }
        }

        for (var item : items) {
            takeOutProductPort.takeOut(item.productId(), item.quantity());
        }

        paymentPort.pay(cmd.userId(), total);

        var order = Order.of(
                cmd.userId(),
                pricedItems,
                cmd.shippingAddress(),
                total,
                Instant.now()
        );
        var savedOrderId = saveOrderPort.save(order);

        return new PlaceOrderResult(
                savedOrderId,
                total.toLong(),
                order.orderDate()
        );
    }

}
