package efr.iv.igr.thriftlimit.feign;

import efr.iv.igr.thriftlimit.model.response.CurrencyApiResponse;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "currency", url="${currencyapi.com}")
public interface CurrencyFeignClient {
    CurrencyApiResponse getCurrency();
}
