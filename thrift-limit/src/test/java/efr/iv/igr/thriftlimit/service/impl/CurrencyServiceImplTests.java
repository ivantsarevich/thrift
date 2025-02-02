package efr.iv.igr.thriftlimit.service.impl;

import efr.iv.igr.thriftlimit.model.entity.Currency;
import efr.iv.igr.thriftlimit.model.entity.CurrencyCode;
import efr.iv.igr.thriftlimit.repository.CurrencyRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceImplTests {
    @Mock
    CurrencyRepository currencyRepository;

    @InjectMocks
    CurrencyServiceImpl currencyService;

    private Currency currency;

    private Currency currencySecond;

    @BeforeEach
    void setUp() {
        currency = new Currency();
        currency.setId(1L);
        currency.setLastUpdate(Instant.now());
        currency.setCode(CurrencyCode.KZT);
        currency.setRate(BigDecimal.valueOf(100));

        currencySecond = new Currency();
        currencySecond.setId(2L);
        currencySecond.setLastUpdate(Instant.now());
        currencySecond.setCode(CurrencyCode.USD);
        currencySecond.setRate(BigDecimal.valueOf(1));
    }

    @Test
    void getCurrencyByCode_ReturnCurrency() {
        Mockito.when(currencyRepository.findFirstByCodeOrderByIdDesc(CurrencyCode.KZT)).thenReturn(currency);

        Currency currencyResponse = currencyService.getCurrency(CurrencyCode.KZT);

        Assertions.assertNotNull(currencyResponse);
        Assertions.assertEquals(currency.getCode(), currencyResponse.getCode());
    }

    @Test
    void convertCurrency() {
        Mockito.when(currencyRepository.findFirstByCodeOrderByIdDesc(CurrencyCode.KZT)).thenReturn(currency);
        Mockito.when(currencyRepository.findFirstByCodeOrderByIdDesc(CurrencyCode.USD)).thenReturn(currencySecond);

        BigDecimal res = currencyService.convertCurrency(CurrencyCode.KZT, BigDecimal.valueOf(200), CurrencyCode.USD);
        BigDecimal expectedResult = BigDecimal.valueOf(2);

        Assertions.assertEquals(expectedResult, res);
    }
}
