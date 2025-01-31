package efr.iv.igr.thriftbank.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import efr.iv.igr.thriftbank.exception.InvalidAmountException;
import efr.iv.igr.thriftbank.exception.SimilarIndetifierException;
import efr.iv.igr.thriftbank.model.entity.Category;
import efr.iv.igr.thriftbank.model.entity.CurrencyCode;
import efr.iv.igr.thriftbank.model.request.TransactionRequest;
import efr.iv.igr.thriftbank.model.response.TransactionResponse;
import efr.iv.igr.thriftbank.service.impl.TransactionServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class TransactionControllerTests {
    @Mock
    private TransactionServiceImpl transactionService;

    @InjectMocks
    private TransactionController transactionController;

    @Test
    void createTransactionExceptionTest() throws SimilarIndetifierException, InvalidAmountException {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(BigDecimal.valueOf(200));
        transactionRequest.setCurrency(CurrencyCode.USD);
        transactionRequest.setCategory(Category.Product);
        transactionRequest.setAccountFrom(1L);
        transactionRequest.setAccountTo(1L);

        Mockito.doThrow(SimilarIndetifierException.class)
                .when(transactionService)
                .createTransaction(transactionRequest);

        Assertions.assertThrows(SimilarIndetifierException.class, () -> {
            transactionController.createTransaction(transactionRequest);
        });
    }

    @Test
    void createTransactionSuccessTest() throws InvalidAmountException, SimilarIndetifierException {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(BigDecimal.valueOf(200));
        transactionRequest.setCurrency(CurrencyCode.USD);
        transactionRequest.setCategory(Category.Product);
        transactionRequest.setAccountFrom(1L);
        transactionRequest.setAccountTo(1L);

        Mockito.doReturn(new TransactionResponse())
                .when(transactionService)
                .createTransaction(transactionRequest);

        TransactionResponse transactionResponse = transactionController.createTransaction(transactionRequest);

        Assertions.assertNotNull(transactionResponse);
    }

    @Test
    void getAllTransactionsSuccessTest() throws JsonProcessingException {
        TransactionResponse transactionResponse1 = new TransactionResponse();
        transactionResponse1.setId(1L);
        transactionResponse1.setDate(Instant.now());
        transactionResponse1.setAmount(BigDecimal.valueOf(200));
        transactionResponse1.setCurrency(CurrencyCode.USD);
        transactionResponse1.setCategory(Category.Product);
        transactionResponse1.setAccountFrom(1L);
        transactionResponse1.setAccountTo(2L);

        TransactionResponse transactionResponse2 = new TransactionResponse();
        transactionResponse2.setId(1L);
        transactionResponse2.setDate(Instant.now());
        transactionResponse2.setAmount(BigDecimal.valueOf(200));
        transactionResponse2.setCurrency(CurrencyCode.USD);
        transactionResponse2.setCategory(Category.Product);
        transactionResponse2.setAccountFrom(2L);
        transactionResponse2.setAccountTo(1L);

        List<TransactionResponse> transactionResponseList = List.of(transactionResponse1, transactionResponse2);

        Mockito.doReturn(transactionResponseList)
                .when(transactionService)
                .getAllTransactions();

        List<TransactionResponse> transactions = transactionController.getTransactions();

        Assertions.assertNotNull(transactions);
        Assertions.assertEquals(transactionResponseList, transactions);
    }
}
