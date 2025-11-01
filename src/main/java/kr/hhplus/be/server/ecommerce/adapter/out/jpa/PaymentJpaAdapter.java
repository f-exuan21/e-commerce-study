package kr.hhplus.be.server.ecommerce.adapter.out.jpa;

import kr.hhplus.be.server.ecommerce.adapter.out.jpa.entity.PointEntity;
import kr.hhplus.be.server.ecommerce.adapter.out.jpa.entity.PointHistoryEntity;
import kr.hhplus.be.server.ecommerce.application.port.out.PaymentPort;
import kr.hhplus.be.server.ecommerce.domain.model.Money;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class PaymentJpaAdapter implements PaymentPort {

    private final JpaPointHistoryRepository pointHistoryRepository;
    private final JpaPointRepository pointRepository;

    public PaymentJpaAdapter(JpaPointHistoryRepository pointHistoryRepository, JpaPointRepository pointRepository) {
        this.pointHistoryRepository = pointHistoryRepository;
        this.pointRepository = pointRepository;
    }

    @Override
    public void pay(Long userId, Money amount) {
        pointRepository.findById(userId).orElseGet(() -> pointRepository.save(new PointEntity(userId, 0)));
        int delta = Math.toIntExact(amount.value().longValue());

        int updated = pointRepository.decreaseIfEnough(userId, delta);
        if (updated == 0) {
            throw new IllegalStateException("Insufficient point: userId = " + userId + ", delta = " + delta);
        }

        pointHistoryRepository.save(new PointHistoryEntity(userId, delta, "PAYMENT", Instant.now()));
    }

}
