package efr.iv.igr.thriftlimit.service.impl;

import efr.iv.igr.thriftlimit.exception.ConnectTransactionServiceException;
import efr.iv.igr.thriftlimit.exception.InvalidLimitAmountException;
import efr.iv.igr.thriftlimit.feign.TransactionFeignClient;
import efr.iv.igr.thriftlimit.mapper.LimitMapper;
import efr.iv.igr.thriftlimit.model.entity.Category;
import efr.iv.igr.thriftlimit.model.entity.CurrencyCode;
import efr.iv.igr.thriftlimit.model.entity.Limit;
import efr.iv.igr.thriftlimit.model.request.LimitRequest;
import efr.iv.igr.thriftlimit.model.response.LimitResponse;
import efr.iv.igr.thriftlimit.model.response.TransactionExceededResponse;
import efr.iv.igr.thriftlimit.model.response.TransactionResponse;
import efr.iv.igr.thriftlimit.repository.LimitRepository;
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
class LimitServiceImplTests {
    @Mock
    LimitRepository limitRepository;

    @Mock
    LimitMapper limitMapper;

    @Mock
    TransactionFeignClient transactionFeignClient;

    @Mock
    CurrencyServiceImpl currencyService;

    @InjectMocks
    LimitServiceImpl limitService;

    LimitRequest limitRequest;

    LimitResponse limitResponse;

    Limit limit;

    TransactionResponse transactionResponse;

    @BeforeEach
    void setUp() {
        limitRequest = new LimitRequest();
        limitRequest.setLimitCurrency(CurrencyCode.KZT);
        limitRequest.setSumLimit(BigDecimal.valueOf(1000));
        limitRequest.setAccountId(1L);
        limitRequest.setCategory(Category.Product);

        limitResponse = new LimitResponse();
        limitResponse.setLimitCurrency(CurrencyCode.KZT);
        limitResponse.setSumLimit(BigDecimal.valueOf(1000));
        limitResponse.setAccountId(1L);
        limitResponse.setCategory(Category.Product);
        limitResponse.setId(1L);
        limitResponse.setLimitDatetime(Instant.now());

        limit = new Limit();
        limitResponse = new LimitResponse();
        limit.setLimitCurrency(CurrencyCode.KZT);
        limit.setSumLimit(BigDecimal.valueOf(1000));
        limit.setAccountId(1L);
        limit.setCategory(Category.Product);
        limit.setId(1L);
        limit.setLimitDatetime(Instant.now());

        transactionResponse = new TransactionResponse();
        transactionResponse.setDate(Instant.now());
        transactionResponse.setAmount(BigDecimal.valueOf(100));
        transactionResponse.setCurrency(CurrencyCode.KZT);
        transactionResponse.setId(1L);
        transactionResponse.setCategory(Category.Product);
        transactionResponse.setAccountFrom(1L);
        transactionResponse.setAccountTo(1L);
    }

    @Test
    @DisplayName("Call createLimit method with valid data")
    void createLimit_PayloadIsValid_ReturnLimitResponse() throws InvalidLimitAmountException {
        Mockito.when(limitMapper.toEntity(limitRequest)).thenReturn(limit);
        Mockito.when(limitRepository.save(limit)).thenReturn(limit);
        Mockito.when(limitMapper.toResponse(limit)).thenReturn(limitResponse);

        LimitResponse response = limitService.createLimit(limitRequest);

        Assertions.assertNotNull(response);
    }

    @Test
    @DisplayName("Call createLimit method with invalid data")
    void createLimit_PayloadIsInvalid_ReturnInvalidLimitAmountException() {
        limitRequest.setSumLimit(BigDecimal.valueOf(-1));

        Assertions.assertThrows(InvalidLimitAmountException.class,
                () -> limitService.createLimit(limitRequest));
    }

    @Test
    @DisplayName("Call getLimits method")
    void getLimits_ReturnListLimitResponse() {
        Mockito.when(limitRepository.findAll()).thenReturn((List.of(limit)));
        Mockito.when(limitMapper.toResponses(List.of(limit))).thenReturn(List.of(limitResponse));

        List<LimitResponse> list = limitService.getLimits();

        Assertions.assertNotNull(list);
    }

    @Test
    @DisplayName("Call getAllTransactionsExceeded with service unavailable")
    void getAllTransactionsExceeded_ReturnConnectTransactionServiceException() {
        Mockito.when(transactionFeignClient.getTransaction())
                .thenReturn(List.of(transactionResponse));

        Assertions.assertThrows(ConnectTransactionServiceException.class,
                () -> limitService.getAllTransactionsExceeded());
    }

    @Test
    @DisplayName("Call getAllTransactionsExceeded with exceeded limit")
    void getAllTransactionsExceeded_ReturnListTransactionExceededResponse() throws ConnectTransactionServiceException {
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        transactionResponses.add(transactionResponse);

        Mockito.when(transactionFeignClient.getTransaction())
                .thenReturn(transactionResponses);

        Mockito.when(limitRepository.findFirstByAccountIdAndCategoryOrderByIdDesc(1L, Category.Product))
                .thenReturn(limit);

        Mockito.when(limitMapper.toResponse(limit))
                .thenReturn(limitResponse);

        Mockito.when(currencyService.convertCurrency(
                        transactionResponses.getFirst().getCurrency(),
                        transactionResponses.getFirst().getAmount(),
                        limit.getLimitCurrency()))
                .thenReturn(BigDecimal.valueOf(1000));

        List<TransactionExceededResponse> result = limitService.getAllTransactionsExceeded();

        Assertions.assertNotNull(result);
        Assertions.assertTrue(result.getFirst().isExceeded());
    }
}
