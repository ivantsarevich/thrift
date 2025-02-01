package efr.iv.igr.thriftbank.service.impl;

import efr.iv.igr.thriftbank.exception.InvalidAmountException;
import efr.iv.igr.thriftbank.exception.SimilarIndetifierException;
import efr.iv.igr.thriftbank.mapper.TransactionMapper;
import efr.iv.igr.thriftbank.model.entity.Transaction;
import efr.iv.igr.thriftbank.model.request.TransactionRequest;
import efr.iv.igr.thriftbank.model.response.TransactionResponse;
import efr.iv.igr.thriftbank.repository.TransactionRepository;
import efr.iv.igr.thriftbank.service.ICacheTransactionService;
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

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTests {
    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private ICacheTransactionService cacheTransactionService;

    private TransactionRequest transactionRequest;

    private Transaction transaction;

    private TransactionResponse transactionResponse;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    @BeforeEach
    void setUp() {
        transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(BigDecimal.valueOf(200));
        transactionRequest.setAccountFrom(1L);
        transactionRequest.setAccountTo(2L);

        transaction = new Transaction();
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setAccountFrom(transactionRequest.getAccountFrom());
        transaction.setAccountTo(transactionRequest.getAccountTo());
        transaction.setDate(Instant.now());

        transactionResponse = new TransactionResponse();
        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setAccountFrom(transaction.getAccountFrom());
        transactionResponse.setAccountTo(transaction.getAccountTo());
        transactionResponse.setDate(transaction.getDate());
    }

    @Test
    @DisplayName("Call createTransaction method with the same account ID")
    void createTransaction_PayloadIsNotValid_ReturnSimilarIndetifierException() {
        transactionRequest.setAccountFrom(2L);

        Assertions.assertThrows(SimilarIndetifierException.class,
                () -> transactionService.createTransaction(transactionRequest));
        Mockito.verifyNoMoreInteractions(transactionRepository, transactionMapper, cacheTransactionService);
    }

    @Test
    @DisplayName("Call createTransaction method with an incorrect amount")
    void createTransaction_PayloadIsNotValid_ReturnInvalidAmountException() {
        transactionRequest.setAmount(BigDecimal.valueOf(99));

        Assertions.assertThrows(InvalidAmountException.class,
                () -> transactionService.createTransaction(transactionRequest));
        Mockito.verifyNoMoreInteractions(transactionRepository, transactionMapper, cacheTransactionService);
    }

    @Test
    @DisplayName("Call createTransaction method with valid data")
    void createTransaction_PayloadIsValid_ReturnValidTransaction() throws SimilarIndetifierException, InvalidAmountException {
        Mockito.when(transactionMapper.toEntity(transactionRequest)).thenReturn(transaction);
        Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction);
        Mockito.when(transactionMapper.toResponse(transaction)).thenReturn(transactionResponse);

        TransactionResponse response = transactionService.createTransaction(transactionRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(transactionRequest.getAmount(), response.getAmount());
        Mockito.verify(cacheTransactionService).deleteCacheData();
        Mockito.verify(transactionRepository).save(transaction);
    }
}
