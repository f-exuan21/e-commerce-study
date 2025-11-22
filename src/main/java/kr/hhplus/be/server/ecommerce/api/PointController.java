package kr.hhplus.be.server.ecommerce.api;

import kr.hhplus.be.server.ecommerce.application.port.dto.BalanceResult;
import kr.hhplus.be.server.ecommerce.application.port.dto.ChargeCommand;
import kr.hhplus.be.server.ecommerce.application.port.in.ChargePointUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/points")
public class PointController {

    private final ChargePointUseCase chargePointUseCase;

    public PointController(ChargePointUseCase chargePointUseCase) {
        this.chargePointUseCase = chargePointUseCase;
    }

    public record PointBalanceRequest(int balance) {
    }

    @PostMapping("/{userId}/charge")
    public ResponseEntity<BalanceResult> chargePoint(@PathVariable long userId,
                                                     @RequestBody PointBalanceRequest request) {
        BalanceResult result = chargePointUseCase.charge(new ChargeCommand(userId, request.balance()));
        return ResponseEntity.ok(result);
    }

}
