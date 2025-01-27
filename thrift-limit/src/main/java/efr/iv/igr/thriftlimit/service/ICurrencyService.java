package efr.iv.igr.thriftlimit.service;

import efr.iv.igr.thriftlimit.model.entity.Currency;

public interface ICurrencyService {
    Currency createCurrency(Currency currency);

    Currency getCurrency(String code);
}
