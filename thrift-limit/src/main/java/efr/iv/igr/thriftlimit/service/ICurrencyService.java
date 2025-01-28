package efr.iv.igr.thriftlimit.service;

import efr.iv.igr.thriftlimit.model.entity.Currency;
import efr.iv.igr.thriftlimit.model.entity.CurrencyCode;

public interface ICurrencyService {
    Currency createCurrency(Currency currency);

    Currency getCurrency(CurrencyCode code);
}
