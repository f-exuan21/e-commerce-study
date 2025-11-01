package kr.hhplus.be.server.ecommerce.application.port.in;

import kr.hhplus.be.server.ecommerce.application.port.dto.PlaceOrderCommand;
import kr.hhplus.be.server.ecommerce.application.port.dto.PlaceOrderResult;


public interface PlaceOrderUseCase {

    PlaceOrderResult placeOrder(PlaceOrderCommand command);

}
