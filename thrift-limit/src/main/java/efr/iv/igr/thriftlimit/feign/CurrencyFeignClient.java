package efr.iv.igr.thriftlimit.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "currency", url="${currencyapi.com}")
public interface CurrencyFeignClient {
}
