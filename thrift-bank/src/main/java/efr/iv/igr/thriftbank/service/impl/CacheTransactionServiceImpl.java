package efr.iv.igr.thriftbank.service.impl;

import efr.iv.igr.thriftbank.model.entity.CacheTransaction;
import efr.iv.igr.thriftbank.repository.CacheTransactionRepository;
import efr.iv.igr.thriftbank.service.ICacheTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheTransactionServiceImpl implements ICacheTransactionService {
    private final CacheTransactionRepository cacheTransactionRepository;

    @Autowired
    public CacheTransactionServiceImpl(CacheTransactionRepository cacheTransactionRepository) {
        this.cacheTransactionRepository = cacheTransactionRepository;
    }

    @Override
    public void createCacheData(String data) {
        deleteCacheData();
        cacheTransactionRepository.save(new CacheTransaction("allTransactions", data));
    }

    @Override
    public CacheTransaction getCacheData() {
        return cacheTransactionRepository.findById("allTransactions").orElse(null);
    }

    @Override
    public void deleteCacheData() {
        cacheTransactionRepository.deleteById("allTransactions");
    }
}
