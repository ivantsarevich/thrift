package efr.iv.igr.thriftbank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import efr.iv.igr.thriftbank.mapper.TransactionMapper;
import efr.iv.igr.thriftbank.model.entity.Transaction;
import efr.iv.igr.thriftbank.model.request.TransactionRequest;
import efr.iv.igr.thriftbank.model.response.TransactionResponse;
import efr.iv.igr.thriftbank.service.ICacheTransactionService;
import efr.iv.igr.thriftbank.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
public class TransactionController {
    private final ITransactionService transactionService;

    private final TransactionMapper transactionMapper;

    private final ICacheTransactionService cacheDataService;

    private final ObjectMapper objectMapper;

    @Autowired
    public TransactionController(ITransactionService transactionService, TransactionMapper transactionMapper,
                                 ICacheTransactionService cacheDataService, ObjectMapper objectMapper) {
        this.transactionService = transactionService;
        this.transactionMapper = transactionMapper;
        this.cacheDataService = cacheDataService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public List<TransactionResponse> getTransactions() throws JsonProcessingException {
        if (cacheDataService.getCacheData() == null) {
            List<Transaction> transactions = transactionService.getAllTransactions();
            String transactionsAsString = objectMapper.writeValueAsString(transactions);
            cacheDataService.createCacheData(transactionsAsString);

            return transactionMapper.toResponses(transactions);
        }

        String transactionsAsString = cacheDataService.getCacheData().getValue();
        TypeReference<List<Transaction>> typeReference = new TypeReference<>() {};
        List<Transaction> transactions = objectMapper.readValue(transactionsAsString, typeReference);

        return transactionMapper.toResponses(transactions);
    }

    @PostMapping
    public TransactionResponse createTransaction(@RequestBody TransactionRequest transactionRequest) {
        cacheDataService.deleteCacheData();
        Transaction transaction = transactionMapper.toEntity(transactionRequest);
        return transactionMapper.toResponse(transactionService.createTransaction(transaction));
    }
}
