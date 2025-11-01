package kr.hhplus.be.server.ecommerce.application.port.out;

public interface PointBalancePort {

    int increase(long userId, int delta);

    int decreaseOrThrow(long userId, int delta);

    int getBalance(long userId);

}
