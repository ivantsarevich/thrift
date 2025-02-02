package efr.iv.igr.thriftlimit.model.response;

import lombok.Data;

@Data
public class TransactionExceededResponse {
    private TransactionResponse transactionResponse;

    private LimitResponse limitResponse;

    private boolean exceeded;
}
