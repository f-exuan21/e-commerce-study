package kr.hhplus.be.server.ecommerce.adapter.out.jpa.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@Entity
@Table(name = "points")
public class PointEntity {

    @Id @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false)
    private int point;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected PointEntity() {
    }

    public PointEntity(Long userId, int point) {
        if (point < 0) throw new IllegalArgumentException("point must be greater than 0");
        this.userId = userId;
        this.point = point;
    }

    public Long getUserId() {
        return userId;
    }

    public int getPoint() {
        return point;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}
