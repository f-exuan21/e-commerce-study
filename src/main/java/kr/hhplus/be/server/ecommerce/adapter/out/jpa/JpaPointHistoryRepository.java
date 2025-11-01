package kr.hhplus.be.server.ecommerce.adapter.out.jpa;

import kr.hhplus.be.server.ecommerce.adapter.out.jpa.entity.PointHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaPointHistoryRepository extends JpaRepository<PointHistoryEntity, Long> {



}
