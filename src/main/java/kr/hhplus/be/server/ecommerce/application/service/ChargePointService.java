package kr.hhplus.be.server.ecommerce.application.service;

import kr.hhplus.be.server.ecommerce.application.port.dto.BalanceResult;
import kr.hhplus.be.server.ecommerce.application.port.dto.ChargeCommand;
import kr.hhplus.be.server.ecommerce.application.port.in.ChargePointUseCase;
import kr.hhplus.be.server.ecommerce.application.port.out.PointBalancePort;
import kr.hhplus.be.server.ecommerce.application.port.out.SavePointHistoryPort;
import kr.hhplus.be.server.ecommerce.domain.model.PointEvent;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ChargePointService implements ChargePointUseCase {

    private final PointBalancePort pointBalancePort;
    private final SavePointHistoryPort savePointHistoryPort;

    public ChargePointService(PointBalancePort pointBalancePort, SavePointHistoryPort savePointHistoryPort) {
        this.pointBalancePort = pointBalancePort;
        this.savePointHistoryPort = savePointHistoryPort;
    }

    @Override
    public BalanceResult charge(ChargeCommand command) {
        int newPoint = pointBalancePort.increase(command.userId(), command.delta());
        savePointHistoryPort.save(new PointEvent(command.userId(), command.delta(), "CHARGE", Instant.now()));
        return new BalanceResult(command.userId(), newPoint);
    }
}
