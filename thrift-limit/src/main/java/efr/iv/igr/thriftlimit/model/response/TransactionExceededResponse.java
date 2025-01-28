package efr.iv.igr.thriftlimit.model.response;

import lombok.Data;

@Data
public class TransactionExceededResponse {
    private LimitResponse limitResponse;

    private TransactionResponse transactionResponse;

    private boolean exceeded;
}
