package efr.iv.igr.thriftlimit.feign;

import efr.iv.igr.thriftlimit.model.response.TransactionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "transaction", url="${transaction.url}")
public interface TransactionFeignClient {
    @GetMapping
    List<TransactionResponse> getTransaction();
}
