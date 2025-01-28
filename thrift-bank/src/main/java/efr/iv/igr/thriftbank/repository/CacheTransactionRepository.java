package efr.iv.igr.thriftbank.repository;

import efr.iv.igr.thriftbank.model.entity.CacheTransaction;
import org.springframework.data.repository.CrudRepository;

public interface CacheTransactionRepository extends CrudRepository<CacheTransaction, String> {
}
