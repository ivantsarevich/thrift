package efr.iv.igr.thriftbank.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import efr.iv.igr.thriftbank.exception.InvalidAmountException;
import efr.iv.igr.thriftbank.exception.SimilarIndetifierException;
import efr.iv.igr.thriftbank.mapper.TransactionMapper;
import efr.iv.igr.thriftbank.model.entity.Transaction;
import efr.iv.igr.thriftbank.model.request.TransactionRequest;
import efr.iv.igr.thriftbank.model.response.TransactionResponse;
import efr.iv.igr.thriftbank.repository.TransactionRepository;
import efr.iv.igr.thriftbank.service.ICacheTransactionService;
import efr.iv.igr.thriftbank.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class TransactionServiceImpl implements ITransactionService {
    private final TransactionRepository transactionRepository;

    private final TransactionMapper transactionMapper;

    private final ICacheTransactionService cacheTransactionService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, TransactionMapper transactionMapper,
                                  ICacheTransactionService cacheTransactionService) {
        this.transactionRepository = transactionRepository;
        this.transactionMapper = transactionMapper;
        this.cacheTransactionService = cacheTransactionService;
    }

    @Override
    public TransactionResponse createTransaction(TransactionRequest transactionRequest) throws InvalidAmountException, SimilarIndetifierException {
        if (transactionRequest.getAmount().compareTo(BigDecimal.valueOf(100)) <= 0) {
            throw new InvalidAmountException("Amount must be bigger than 100");
        }

        if (transactionRequest.getAccountFrom().compareTo(transactionRequest.getAccountTo()) == 0) {
            throw new SimilarIndetifierException("You can't transfer money to yourself");
        }

        cacheTransactionService.deleteCacheData();
        Transaction transaction = transactionMapper.toEntity(transactionRequest);
        transaction.setDate(Instant.now());
        return transactionMapper.toResponse(transactionRepository.save(transaction));
    }

    @Override
    public List<TransactionResponse> getAllTransactions() throws JsonProcessingException {
        List<TransactionResponse> cacheData = cacheTransactionService.getCacheData();

        if (cacheData.isEmpty()) {
            cacheTransactionService.createCacheData(transactionMapper.toResponses(transactionRepository.findAll()));
            return transactionMapper.toResponses(transactionRepository.findAll());
        }

        return cacheData;
    }
}
