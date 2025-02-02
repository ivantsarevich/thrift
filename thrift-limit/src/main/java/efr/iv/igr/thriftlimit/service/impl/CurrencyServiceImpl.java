package efr.iv.igr.thriftlimit.service.impl;

import efr.iv.igr.thriftlimit.model.entity.Currency;
import efr.iv.igr.thriftlimit.model.entity.CurrencyCode;
import efr.iv.igr.thriftlimit.repository.CurrencyRepository;
import efr.iv.igr.thriftlimit.service.ICurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CurrencyServiceImpl implements ICurrencyService {
    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void createCurrency(Currency currency) {
        currencyRepository.save(currency);
    }

    @Override
    public Currency getCurrency(CurrencyCode code) {
        return currencyRepository.findFirstByCodeOrderByIdDesc(code);
    }

    @Override
    public BigDecimal convertCurrency(CurrencyCode from, BigDecimal amount, CurrencyCode to) {
        Currency currencyFrom = getCurrency(from);
        Currency currencyTo = getCurrency(to);

        return amount.multiply(currencyTo.getRate()).divide(currencyFrom.getRate(), RoundingMode.CEILING);
    }
}
