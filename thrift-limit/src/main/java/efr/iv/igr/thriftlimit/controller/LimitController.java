package efr.iv.igr.thriftlimit.controller;

import efr.iv.igr.thriftlimit.mapper.LimitMapper;
import efr.iv.igr.thriftlimit.model.entity.Limit;
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

    private final LimitMapper limitMapper;

    @Autowired
    public LimitController(ILimitService limitService, LimitMapper limitMapper) {
        this.limitService = limitService;
        this.limitMapper = limitMapper;
    }

    @GetMapping
    public List<LimitResponse> getLimits() {
        return limitMapper.toResponses(limitService.getLimits());
    }

    @PostMapping
    public LimitResponse createLimit(@RequestBody LimitRequest limitRequest) {
        Limit limit = limitMapper.toEntity(limitRequest);
        return limitMapper.toResponse(limitService.createLimit(limit));
    }

    @GetMapping("/transactions/exceeded")
    public List<TransactionExceededResponse> getAllTransactionsExceeded() {
        return limitService.getAllTransactionsExceeded();
    }
}
