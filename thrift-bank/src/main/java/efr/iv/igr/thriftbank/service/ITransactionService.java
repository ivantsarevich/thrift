package efr.iv.igr.thriftbank.service;

import efr.iv.igr.thriftbank.model.entity.Transaction;

import java.util.List;

public interface ITransactionService {
    Transaction createTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();
}
