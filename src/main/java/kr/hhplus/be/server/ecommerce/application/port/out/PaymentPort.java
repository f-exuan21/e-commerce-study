package kr.hhplus.be.server.ecommerce.application.port.out;

import kr.hhplus.be.server.ecommerce.domain.model.Money;

public interface PaymentPort {

    void pay(Long userId, Money amount);

}
