package efr.iv.igr.thriftlimit.controller;

import efr.iv.igr.thriftlimit.exception.ConnectTransactionServiceException;
import efr.iv.igr.thriftlimit.exception.InvalidLimitAmountException;
import efr.iv.igr.thriftlimit.model.request.LimitRequest;
import efr.iv.igr.thriftlimit.model.response.LimitResponse;
import efr.iv.igr.thriftlimit.model.response.TransactionExceededResponse;
import efr.iv.igr.thriftlimit.service.ILimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/limits")
public class LimitController {
    private final ILimitService limitService;

    @Autowired
    public LimitController(ILimitService limitService) {
        this.limitService = limitService;
    }

    @GetMapping
    public List<LimitResponse> getLimits() {
        return limitService.getLimits();
    }

    @PostMapping
    public LimitResponse createLimit(@RequestBody LimitRequest limitRequest) throws InvalidLimitAmountException {
        return limitService.createLimit(limitRequest);
    }

    @GetMapping("/transactions/exceeded")
    public List<TransactionExceededResponse> getAllTransactionsExceeded() throws ConnectTransactionServiceException {
        return limitService.getAllTransactionsExceeded();
    }
}
