package efr.iv.igr.thriftlimit.service;

import efr.iv.igr.thriftlimit.model.request.LimitRequest;
import efr.iv.igr.thriftlimit.model.response.LimitResponse;
import efr.iv.igr.thriftlimit.model.response.TransactionExceededResponse;

import java.util.List;

public interface ILimitService {
    LimitResponse createLimit(LimitRequest limitRequest);

    List<LimitResponse> getLimits();

    List<TransactionExceededResponse> getAllTransactionsExceeded();
}
