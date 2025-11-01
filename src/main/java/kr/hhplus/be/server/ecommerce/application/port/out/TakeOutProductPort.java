package kr.hhplus.be.server.ecommerce.application.port.out;

public interface TakeOutProductPort {

    void takeOut(Long productId, int quantity);

}
