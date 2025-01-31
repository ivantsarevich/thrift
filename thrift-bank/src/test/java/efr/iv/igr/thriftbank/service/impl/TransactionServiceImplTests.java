package efr.iv.igr.thriftbank.service.impl;

import efr.iv.igr.thriftbank.exception.InvalidAmountException;
import efr.iv.igr.thriftbank.exception.SimilarIndetifierException;
import efr.iv.igr.thriftbank.model.entity.Category;
import efr.iv.igr.thriftbank.model.entity.CurrencyCode;
import efr.iv.igr.thriftbank.model.request.TransactionRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTests {
    @InjectMocks
    private TransactionServiceImpl transactionService;

    @Test
    void createTransactionSimilarIndetifierExceptionTest() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(BigDecimal.valueOf(200));
        transactionRequest.setCurrency(CurrencyCode.USD);
        transactionRequest.setCategory(Category.Product);
        transactionRequest.setAccountFrom(1L);
        transactionRequest.setAccountTo(1L);

        Assertions.assertThrows(SimilarIndetifierException.class, () -> transactionService.createTransaction(transactionRequest));
    }

    @Test
    void createTransactionInvalidAmountExceptionTest() {
        TransactionRequest transactionRequest = new TransactionRequest();
        transactionRequest.setAmount(BigDecimal.valueOf(100));
        transactionRequest.setCurrency(CurrencyCode.USD);
        transactionRequest.setCategory(Category.Product);
        transactionRequest.setAccountFrom(1L);
        transactionRequest.setAccountTo(2L);

        Assertions.assertThrows(InvalidAmountException.class, () -> transactionService.createTransaction(transactionRequest));
    }
}
