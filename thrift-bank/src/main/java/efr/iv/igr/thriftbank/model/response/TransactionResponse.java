package efr.iv.igr.thriftbank.model.response;

import efr.iv.igr.thriftbank.model.entity.Category;
import efr.iv.igr.thriftbank.model.entity.CurrencyCode;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class TransactionResponse {
    private Long id;

    private Long accountFrom;

    private Long accountTo;

    private CurrencyCode currency;

    private BigDecimal amount;

    private Category category;

    private Instant date;
}
