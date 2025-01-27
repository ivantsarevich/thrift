package efr.iv.igr.thriftlimit.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "transaction", url="${transaction.url}")
public interface TransactionFeignClient {
}
