package kr.hhplus.be.server.ecommerce.domain.model.money;

import java.math.BigDecimal;

public record Money(BigDecimal value) {

    public static Money of(long won) {
        return new Money(BigDecimal.valueOf(won));
    }

    public Money plus(Money other) {
        return new Money(this.value.add(other.value));
    }

    public Money minus(Money other) {
        return new Money(this.value.subtract(other.value));
    }

    public long toLong() {
        return this.value.longValueExact();
    }

    public Money nonNegative() {
        return this.value.signum() >= 0 ? this : new Money(BigDecimal.ZERO);
    }

}
