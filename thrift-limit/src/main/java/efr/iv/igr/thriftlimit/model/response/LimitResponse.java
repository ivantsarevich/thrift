package efr.iv.igr.thriftlimit.model.response;

import efr.iv.igr.thriftlimit.model.entity.Category;
import efr.iv.igr.thriftlimit.model.entity.CurrencyCode;
import efr.iv.igr.thriftlimit.model.entity.Limit;
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

    public LimitResponse(Limit limit) {
        this.id = limit.getId();
        this.accountId = limit.getAccountId();
        this.sumLimit = limit.getSumLimit();
        this.category = limit.getCategory();
        this.limitCurrency = limit.getLimitCurrency();
        this.limitDatetime = limit.getLimitDatetime();
    }
}
