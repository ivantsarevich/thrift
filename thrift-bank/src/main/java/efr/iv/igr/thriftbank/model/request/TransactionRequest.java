package efr.iv.igr.thriftbank.model.request;

import efr.iv.igr.thriftbank.model.entity.Category;
import efr.iv.igr.thriftbank.model.entity.CurrencyCode;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TransactionRequest {
    private Long accountFrom;

    private Long accountTo;

    private CurrencyCode currency;

    private BigDecimal amount;

    private Category category;
}
