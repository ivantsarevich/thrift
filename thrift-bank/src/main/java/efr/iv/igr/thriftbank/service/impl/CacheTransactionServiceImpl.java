package efr.iv.igr.thriftbank.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import efr.iv.igr.thriftbank.model.entity.CacheTransaction;
import efr.iv.igr.thriftbank.model.response.TransactionResponse;
import efr.iv.igr.thriftbank.repository.CacheTransactionRepository;
import efr.iv.igr.thriftbank.service.ICacheTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheTransactionServiceImpl implements ICacheTransactionService {
    private final CacheTransactionRepository cacheTransactionRepository;

    private final ObjectMapper objectMapper;

    @Autowired
    public CacheTransactionServiceImpl(CacheTransactionRepository cacheTransactionRepository, ObjectMapper objectMapper) {
        this.cacheTransactionRepository = cacheTransactionRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void createCacheData(List<TransactionResponse> transactionResponses) throws JsonProcessingException {
        String transactionsAsString = objectMapper.writeValueAsString(transactionResponses);
        cacheTransactionRepository.save(new CacheTransaction("allTransactions", transactionsAsString));
    }

    @Override
    public List<TransactionResponse> getCacheData() throws JsonProcessingException {
        CacheTransaction allTransactions = cacheTransactionRepository.findById("allTransactions").orElse(null);
        String transactionsAsString = objectMapper.writeValueAsString(allTransactions);
        TypeReference<List<TransactionResponse>> typeReference = new TypeReference<>() {};
        return objectMapper.readValue(transactionsAsString, typeReference);
    }

    @Override
    public void deleteCacheData() {
        cacheTransactionRepository.deleteById("allTransactions");
    }
}
