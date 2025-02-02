package efr.iv.igr.thriftlimit.feign;

import efr.iv.igr.thriftlimit.model.response.CurrencyApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
class TransactionFeignRestClientIT {
    @Autowired
    CurrencyFeignClient currencyFeignClient;

    @Test
    void getCurrency__ReturnValidData() throws Exception {
        CurrencyApiResponse currency = currencyFeignClient.getCurrency();

        Assertions.assertNotNull(currency);
    }
}
