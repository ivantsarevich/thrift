package efr.iv.igr.thriftlimit.service;

import efr.iv.igr.thriftlimit.model.entity.Limit;
import efr.iv.igr.thriftlimit.model.response.TransactionExceededResponse;

import java.util.List;

public interface ILimitService {
    Limit createLimit(Limit limit);

    List<Limit> getLimits();

    List<TransactionExceededResponse> getAllTransactionsExceeded();
}
