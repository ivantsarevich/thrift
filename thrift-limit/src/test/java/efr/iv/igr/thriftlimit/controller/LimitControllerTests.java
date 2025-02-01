package efr.iv.igr.thriftlimit.controller;

import efr.iv.igr.thriftlimit.exception.InvalidLimitAmountException;
import efr.iv.igr.thriftlimit.model.entity.Category;
import efr.iv.igr.thriftlimit.model.entity.CurrencyCode;
import efr.iv.igr.thriftlimit.model.request.LimitRequest;
import efr.iv.igr.thriftlimit.model.response.LimitResponse;
import efr.iv.igr.thriftlimit.service.impl.LimitServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class LimitControllerTests {
    @Mock
    private LimitServiceImpl limitService;

    @InjectMocks
    private LimitController limitController;

    private List<LimitResponse> limits;

    private LimitRequest limitRequest;

    @BeforeEach
    void setUp() {
        limits = new ArrayList<>();
        LimitResponse limitResponse = new LimitResponse();
        limitResponse.setId(1L);
        limitResponse.setLimitCurrency(CurrencyCode.KZT);
        limitResponse.setSumLimit(BigDecimal.valueOf(1000));
        limitResponse.setAccountId(1L);
        limitResponse.setCategory(Category.Product);
        limitResponse.setLimitDatetime(Instant.now());

        limitRequest = new LimitRequest();
        limitRequest.setLimitCurrency(CurrencyCode.KZT);
        limitRequest.setSumLimit(BigDecimal.valueOf(1000));
        limitRequest.setAccountId(1L);
        limitRequest.setCategory(Category.Product);
    }

    @Test
    @DisplayName("Get /api/v1/limits return all limit response entities")
    void getLimits_ReturnValidLimits() {
        Mockito.when(limitService.getLimits()).thenReturn(limits);

        List<LimitResponse> limitResponseList = limitController.getLimits();

        Assertions.assertNotNull(limitResponseList);
        Assertions.assertEquals(limits, limitResponseList);
    }

    @Test
    @DisplayName("Post /api/v1/limits return response entity")
    void createLimit_ReturnValidLimit() throws InvalidLimitAmountException {
        Mockito.when(limitService.createLimit(limitRequest)).thenReturn(new LimitResponse());

        LimitResponse limitResponse = limitController.createLimit(limitRequest);

        Assertions.assertNotNull(limitResponse);
    }

    @Test
    @DisplayName("Post /api/v1/limits return exception invalid amount limit exception")
    void createLimit_PayloadIsNotValid_ReturnInvalidAmountLimitException() throws InvalidLimitAmountException {
        limitRequest.setSumLimit(BigDecimal.valueOf(-1));

        Mockito.when(limitService.createLimit(limitRequest))
                .thenThrow(new InvalidLimitAmountException("Invalid limit amount"));

        Assertions.assertThrows(InvalidLimitAmountException.class, () -> limitController.createLimit(limitRequest));
    }
}
