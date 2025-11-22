package kr.hhplus.be.server.ecommerce.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "point_history")
public class PointHistoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", nullable = false)
    private Long userId;

    @Column(name="delta", nullable = false)
    private int delta;

    @Column(name="reason", length=100)
    private String reason;

    @Column(name="occurred_at", nullable = false)
    private Instant occurredAt;

    protected PointHistoryEntity() {
    }

    public PointHistoryEntity(Long userId, int delta, String reason, Instant occurredAt) {
        this.userId = userId;
        this.delta = delta;
        this.reason = reason;
        this.occurredAt = occurredAt;
    }
}
