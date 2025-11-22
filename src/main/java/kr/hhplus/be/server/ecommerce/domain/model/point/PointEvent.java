package kr.hhplus.be.server.ecommerce.domain.model.point;

import java.time.Instant;

public record PointEvent(Long userId, int delta, String reason, Instant occurredAt) {
}
