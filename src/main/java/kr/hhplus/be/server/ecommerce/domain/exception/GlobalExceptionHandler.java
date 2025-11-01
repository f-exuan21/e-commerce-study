package kr.hhplus.be.server.ecommerce.domain.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(OutOfStockException.class)
    public ResponseEntity<Map<String, Object>> handleOutOfStockException(OutOfStockException e) {
        return ResponseEntity.badRequest().body(
                Map.of(
                        "errorCode", "OUT_OF_STOCK",
                        "errorMessage", "선택하신 상품의 재고가 부족합니다."
                ));
    }

    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFoundException(ProductNotFound e) {
        return ResponseEntity.badRequest().body(
                Map.of(
                        "errorCode", "PRODUCT_NOT_FOUND",
                        "errorMessage", "해당 상품이 존재하지 않습니다."
                ));

    }

    @ExceptionHandler(InsufficientPointException.class)
    public ResponseEntity<Map<String, Object>> handleInsufficientPointException(InsufficientPointException e) {
        return ResponseEntity.badRequest().body(
                Map.of(
                    "errorCode", "INSUFFICIENT_POINT",
                    "errorMessage", e.getMessage()
                )
        );
    }

    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidAmountException(InvalidAmountException e) {
        return ResponseEntity.badRequest().body(
                Map.of(
                    "errorCode", "INVALID_AMOUNT",
                    "errorMessage", e.getMessage()
                )
        );
    }

}
