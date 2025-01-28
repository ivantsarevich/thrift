package efr.iv.igr.thriftbank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import efr.iv.igr.thriftbank.model.request.TransactionRequest;
import efr.iv.igr.thriftbank.model.response.TransactionResponse;

import java.util.List;

public interface ITransactionService {
    TransactionResponse createTransaction(TransactionRequest transactionRequest);

    List<TransactionResponse> getAllTransactions() throws JsonProcessingException;
}
