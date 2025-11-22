package kr.hhplus.be.server.ecommerce.application.port.out;

import kr.hhplus.be.server.ecommerce.domain.model.point.PointEvent;

public interface SavePointHistoryPort {

    void save(PointEvent pointEvent);

}
