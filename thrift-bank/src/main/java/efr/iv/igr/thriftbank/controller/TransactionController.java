package efr.iv.igr.thriftbank.controller;

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
    public List<TransactionResponse> getTransactions() {
        return transactionService.getAllTransactions();

//        if (cacheDataService.getCacheData() == null) {
//            List<Transaction> transactions = transactionService.getAllTransactions();
//            String transactionsAsString = objectMapper.writeValueAsString(transactions);
//            cacheDataService.createCacheData(transactionsAsString);
//
//            return transactionMapper.toResponses(transactions);
//        }
//
//        String transactionsAsString = cacheDataService.getCacheData().getValue();
//        TypeReference<List<Transaction>> typeReference = new TypeReference<>() {};
//        List<Transaction> transactions = objectMapper.readValue(transactionsAsString, typeReference);
//
//        return transactionMapper.toResponses(transactions);
    }

    @PostMapping
    public TransactionResponse createTransaction(@RequestBody TransactionRequest transactionRequest) {
        return createTransaction(transactionRequest);
    }
}
