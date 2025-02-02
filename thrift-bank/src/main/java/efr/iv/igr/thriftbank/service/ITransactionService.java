package efr.iv.igr.thriftbank.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import efr.iv.igr.thriftbank.exception.InvalidAmountException;
import efr.iv.igr.thriftbank.exception.SimilarIndetifierException;
import efr.iv.igr.thriftbank.model.request.TransactionRequest;
import efr.iv.igr.thriftbank.model.response.TransactionResponse;

import java.util.List;

public interface ITransactionService {
    TransactionResponse createTransaction(TransactionRequest transactionRequest) throws InvalidAmountException, SimilarIndetifierException;

    List<TransactionResponse> getAllTransactions() throws JsonProcessingException;
}
