package efr.iv.igr.thriftlimit.service;

import efr.iv.igr.thriftlimit.exception.ConnectTransactionServiceException;
import efr.iv.igr.thriftlimit.exception.InvalidLimitAmountException;
import efr.iv.igr.thriftlimit.model.request.LimitRequest;
import efr.iv.igr.thriftlimit.model.response.LimitResponse;
import efr.iv.igr.thriftlimit.model.response.TransactionExceededResponse;

import java.util.List;

public interface ILimitService {
    LimitResponse createLimit(LimitRequest limitRequest) throws InvalidLimitAmountException;

    List<LimitResponse> getLimits();

    List<TransactionExceededResponse> getAllTransactionsExceeded() throws ConnectTransactionServiceException;
}
