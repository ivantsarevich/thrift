package efr.iv.igr.thriftbank.repository;

import efr.iv.igr.thriftbank.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
