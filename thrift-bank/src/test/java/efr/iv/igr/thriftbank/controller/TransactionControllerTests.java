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
class TransactionControllerTests {
    @Mock
    private TransactionServiceImpl transactionService;

    @InjectMocks
    private TransactionController transactionController;

    private TransactionRequest transactionRequest;

    private List<TransactionResponse> transactionResponses;

    @BeforeEach
    void setUp() {
        transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(BigDecimal.valueOf(200));
        transactionRequest.setCurrency(CurrencyCode.USD);
        transactionRequest.setCategory(Category.Product);
        transactionRequest.setAccountFrom(1L);
        transactionRequest.setAccountTo(1L);

        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setId(1L);
        transactionResponse.setDate(Instant.now());
        transactionResponse.setAmount(BigDecimal.valueOf(200));
        transactionResponse.setCurrency(CurrencyCode.USD);
        transactionResponse.setCategory(Category.Product);
        transactionResponse.setAccountFrom(1L);
        transactionResponse.setAccountTo(2L);

        transactionResponses = new ArrayList<>();
        transactionResponses.add(transactionResponse);
        transactionResponses.add(transactionResponse);
    }

    @Test
    @DisplayName("Post /api/v1/transaction return transaction response")
    void createTransaction_ReturnValidTransactionResponseEntity() throws InvalidAmountException,
            SimilarIndetifierException {
        Mockito.doReturn(new TransactionResponse())
                .when(transactionService)
                .createTransaction(transactionRequest);

        TransactionResponse transactionResponse = transactionController.createTransaction(transactionRequest);

        Assertions.assertNotNull(transactionResponse);
    }

    @Test
    @DisplayName("Get /api/v1/transactions return all transaction response")
    void getTransactions_ReturnValidTransactionResponseEntity() throws JsonProcessingException {
        Mockito.doReturn(transactionResponses)
                .when(transactionService)
                .getAllTransactions();

        List<TransactionResponse> transactions = transactionController.getTransactions();

        Assertions.assertNotNull(transactions);
        Assertions.assertEquals(transactionResponses, transactions);
    }
}
