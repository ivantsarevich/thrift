package efr.iv.igr.thriftbank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import efr.iv.igr.thriftbank.exception.InvalidAmountException;
import efr.iv.igr.thriftbank.exception.SimilarIndetifierException;
import efr.iv.igr.thriftbank.model.request.TransactionRequest;
import efr.iv.igr.thriftbank.model.response.TransactionResponse;
import efr.iv.igr.thriftbank.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final ITransactionService transactionService;

    @Autowired
    public TransactionController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionResponse> getTransactions() throws JsonProcessingException {
        return transactionService.getAllTransactions();
    }

    @PostMapping
    public TransactionResponse createTransaction(@RequestBody TransactionRequest transactionRequest) throws SimilarIndetifierException, InvalidAmountException {
        return transactionService.createTransaction(transactionRequest);
    }
}
