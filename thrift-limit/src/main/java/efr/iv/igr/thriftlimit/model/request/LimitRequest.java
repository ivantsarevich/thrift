package efr.iv.igr.thriftlimit.model.request;

import efr.iv.igr.thriftlimit.model.entity.Category;
import efr.iv.igr.thriftlimit.model.entity.CurrencyCode;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class LimitRequest {
    private Long accountId;

    private BigDecimal sumLimit;

    private Category category;

    private CurrencyCode limitCurrency;
}
