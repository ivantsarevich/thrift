package efr.iv.igr.thriftlimit.runner;

import efr.iv.igr.thriftlimit.feign.CurrencyFeignClient;
import efr.iv.igr.thriftlimit.model.entity.Currency;
import efr.iv.igr.thriftlimit.model.entity.CurrencyCode;
import efr.iv.igr.thriftlimit.model.response.CurrencyApiResponse;
import efr.iv.igr.thriftlimit.service.ICurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SchedulerCheckRateCurrency implements CommandLineRunner {
    private final CurrencyFeignClient currencyFeignClient;

    private final ICurrencyService currencyService;

    @Autowired
    public SchedulerCheckRateCurrency(CurrencyFeignClient currencyFeignClient, ICurrencyService currencyService) {
        this.currencyFeignClient = currencyFeignClient;
        this.currencyService = currencyService;
    }

    @Override
    public void run(String... args) throws Exception {
        schedule();
    }

    @Scheduled(initialDelay = 1, fixedDelay = 60 * 12, timeUnit = TimeUnit.MINUTES)
    private void schedule() {
        CurrencyApiResponse currencyApiResponse = currencyFeignClient.getCurrency();

        for (CurrencyCode currencyCode : CurrencyCode.values()) {
            Currency currency = new Currency();
            currency.setCode(currencyCode);
            currency.setLastUpdate(currencyApiResponse.getMeta().getLastUpdatedAt());
            currency.setRate(currencyApiResponse.getData().get(currencyCode).getValue());
            currencyService.createCurrency(currency);
        }
    }
}
