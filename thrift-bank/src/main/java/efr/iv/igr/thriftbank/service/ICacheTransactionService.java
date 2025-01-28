package efr.iv.igr.thriftbank.service;

import efr.iv.igr.thriftbank.model.entity.CacheTransaction;

public interface ICacheTransactionService {
    void createCacheData(String data);

    CacheTransaction getCacheData();

    void deleteCacheData();
}
