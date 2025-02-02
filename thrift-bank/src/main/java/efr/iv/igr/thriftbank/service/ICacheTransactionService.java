package efr.iv.igr.thriftbank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import efr.iv.igr.thriftbank.model.response.TransactionResponse;

import java.util.List;

public interface ICacheTransactionService {
    void createCacheData(List<TransactionResponse> transactionResponse) throws JsonProcessingException;

    List<TransactionResponse> getCacheData() throws JsonProcessingException;

    void deleteCacheData();
}
