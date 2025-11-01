package kr.hhplus.be.server.ecommerce.application.port.in;

import kr.hhplus.be.server.ecommerce.application.port.dto.BalanceResult;
import kr.hhplus.be.server.ecommerce.application.port.dto.ChargeCommand;

public interface ChargePointUseCase {
    BalanceResult charge(ChargeCommand command);
}
