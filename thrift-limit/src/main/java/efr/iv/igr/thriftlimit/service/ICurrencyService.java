package efr.iv.igr.thriftlimit.service;

import efr.iv.igr.thriftlimit.model.entity.Currency;
import efr.iv.igr.thriftlimit.model.entity.CurrencyCode;

import java.math.BigDecimal;

public interface ICurrencyService {
    void createCurrency(Currency currency);

    Currency getCurrency(CurrencyCode code);

    BigDecimal convertCurrency(CurrencyCode from, BigDecimal amount, CurrencyCode to);
}
