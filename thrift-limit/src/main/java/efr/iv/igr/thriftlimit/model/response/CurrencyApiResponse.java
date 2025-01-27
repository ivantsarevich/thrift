package efr.iv.igr.thriftlimit.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

@lombok.Data
public class CurrencyApiResponse {
    private Meta meta;

    private Map<String, Currency> data;

    @lombok.Data
    public static class Meta {
        @JsonProperty("last_updated_at")
        private Instant lastUpdatedAt;
    }

    @lombok.Data
    public static class Currency {
        @JsonProperty("code")
        private String code;

        @JsonProperty("value")
        private BigDecimal value;
    }
}
