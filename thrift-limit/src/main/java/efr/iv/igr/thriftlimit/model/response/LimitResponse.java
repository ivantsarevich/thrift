package efr.iv.igr.thriftlimit.model.response;

import efr.iv.igr.thriftlimit.model.entity.Category;
import efr.iv.igr.thriftlimit.model.entity.CurrencyCode;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class LimitResponse {
    private Long id;

    private Long accountId;

    private BigDecimal sumLimit;

    private Category category;

    private CurrencyCode limitCurrency;

    private Instant limitDatetime;
}
