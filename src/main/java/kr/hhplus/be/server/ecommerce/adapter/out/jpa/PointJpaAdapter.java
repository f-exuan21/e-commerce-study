package kr.hhplus.be.server.ecommerce.adapter.out.jpa;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.ecommerce.adapter.out.jpa.entity.PointEntity;
import kr.hhplus.be.server.ecommerce.adapter.out.jpa.entity.PointHistoryEntity;
import kr.hhplus.be.server.ecommerce.application.port.out.PointBalancePort;
import kr.hhplus.be.server.ecommerce.application.port.out.SavePointHistoryPort;
import kr.hhplus.be.server.ecommerce.domain.exception.InsufficientPointException;
import kr.hhplus.be.server.ecommerce.domain.model.PointEvent;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class PointJpaAdapter implements PointBalancePort, SavePointHistoryPort {

    private final JpaPointRepository pointRepository;
    private final JpaPointHistoryRepository pointHistoryRepository;

    public PointJpaAdapter(JpaPointRepository pointRepository, JpaPointHistoryRepository pointHistoryRepository) {
        this.pointRepository = pointRepository;
        this.pointHistoryRepository = pointHistoryRepository;
    }

    @Override
    public int increase(long userId, int delta) {
        pointRepository.findById(userId).orElseGet(() -> pointRepository.save(new PointEntity(userId, 0)));
        pointRepository.increase(userId, delta);
        return pointRepository.findById(userId).map(PointEntity::getPoint).orElse(0);
    }

    @Override
    public int decreaseOrThrow(long userId, int delta) {
        pointRepository.findById(userId).orElseGet(() -> pointRepository.save(new PointEntity(userId, 0)));
        int updated = pointRepository.decreaseIfEnough(userId, delta);
        if (updated == 0) {
            int have = pointRepository.findById(userId).map(PointEntity::getPoint).orElse(0);
            throw new InsufficientPointException(userId, delta, have);
        }
        return pointRepository.findById(userId).map(PointEntity::getPoint).orElse(0);
    }

    @Override
    public int getBalance(long userId) {
        return pointRepository.findById(userId).map(PointEntity::getPoint).orElse(0);
    }

    @Override
    public void save(PointEvent pointEvent) {
        pointHistoryRepository.save(new PointHistoryEntity(
                pointEvent.userId(),
                pointEvent.delta(),
                pointEvent.reason(),
                pointEvent.occurredAt()
        ));
    }
}
