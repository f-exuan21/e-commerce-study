package kr.hhplus.be.server.ecommerce.domain.exception;

public class InsufficientPointException extends RuntimeException {
    public InsufficientPointException(Long userId, int need, int have) {
        super("Insufficient points: user=" + userId + ", need=" + need + ", have=" + have);
    }
}
