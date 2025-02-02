package efr.iv.igr.thriftlimit.feign;

import efr.iv.igr.thriftlimit.model.response.TransactionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Disabled
@SpringBootTest
@AutoConfigureMockMvc
class CurrencyFeignRestClientIT {
    @Autowired
    TransactionFeignClient transactionFeignClient;

    @Test
    void getTransaction__ReturnValidData() throws Exception {
        List<TransactionResponse> transactionResponse = transactionFeignClient.getTransaction();

        Assertions.assertNotNull(transactionResponse);
    }
}
