package kr.hhplus.be.server.ecommerce.adapter.in.web;

import kr.hhplus.be.server.ecommerce.application.port.dto.PlaceOrderCommand;
import kr.hhplus.be.server.ecommerce.application.port.dto.PlaceOrderResult;
import kr.hhplus.be.server.ecommerce.application.port.in.PlaceOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderPayController {

    private final PlaceOrderUseCase placeOrderUseCase;

    public OrderPayController(PlaceOrderUseCase placeOrderUseCase) {
        this.placeOrderUseCase = placeOrderUseCase;
    }

    public record OrderPayRequest(long userId,
                                  List<OrderPayProductRequest> orderItems,
                                  String shippingAddress
                                  ) {}

    public record OrderPayResponse(long orderId,
                                   long totalPrice,
                                   Instant orderAt) {}

    public record OrderPayProductRequest(long productId,
                                          int quantity) {}

    /**
     * 상품 주문 및 결제
     * POST /v1/orders
     */
    @PostMapping()
    public ResponseEntity<OrderPayResponse> placeOrder(@RequestBody OrderPayRequest req) {
        PlaceOrderCommand cmd = new PlaceOrderCommand(
                req.userId(),
                req.orderItems().stream()
                        .map(i -> new PlaceOrderCommand.Item(i.productId(), i.quantity()))
                        .toList(),
                req.shippingAddress()
        );
        PlaceOrderResult r = placeOrderUseCase.placeOrder(cmd);
        return ResponseEntity.ok(new OrderPayResponse(
                r.orderId(),
                r.totalAmount(),
                r.orderAt()
        ));
    }

}
