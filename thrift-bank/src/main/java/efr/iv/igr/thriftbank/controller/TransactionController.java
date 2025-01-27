package efr.iv.igr.thriftbank.controller;

import efr.iv.igr.thriftbank.mapper.TransactionMapper;
import efr.iv.igr.thriftbank.model.entity.Transaction;
import efr.iv.igr.thriftbank.model.request.TransactionRequest;
import efr.iv.igr.thriftbank.model.response.TransactionResponse;
import efr.iv.igr.thriftbank.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final TransactionServiceImpl transactionService;

    private final TransactionMapper transactionMapper;

    @Autowired
    public TransactionController(TransactionServiceImpl transactionService, TransactionMapper transactionMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
    }

    @GetMapping
    public List<TransactionResponse> getTransactions() {
        return transactionMapper.toResponses(transactionService.getAllTransactions());
    }

    @PostMapping
    public TransactionResponse createTransaction(@RequestBody TransactionRequest transactionRequest) {
        Transaction transaction = transactionMapper.toEntity(transactionRequest);
        return transactionMapper.toResponse(transactionService.createTransaction(transaction));
    }
}
