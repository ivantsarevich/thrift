package efr.iv.igr.thriftlimit.feign;

import efr.iv.igr.thriftlimit.model.response.CurrencyApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "currency", url = "${currencyapi.url}")
public interface CurrencyFeignClient {
    @GetMapping
    CurrencyApiResponse getCurrency();
}
