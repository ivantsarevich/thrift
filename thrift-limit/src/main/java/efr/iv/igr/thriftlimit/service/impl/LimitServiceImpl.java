package efr.iv.igr.thriftlimit.service.impl;

import efr.iv.igr.thriftlimit.feign.TransactionFeignClient;
import efr.iv.igr.thriftlimit.mapper.LimitMapper;
import efr.iv.igr.thriftlimit.model.entity.Limit;
import efr.iv.igr.thriftlimit.model.request.LimitRequest;
import efr.iv.igr.thriftlimit.model.response.LimitResponse;
import efr.iv.igr.thriftlimit.model.response.TransactionExceededResponse;
import efr.iv.igr.thriftlimit.model.response.TransactionResponse;
import efr.iv.igr.thriftlimit.repository.LimitRepository;
import efr.iv.igr.thriftlimit.service.ICurrencyService;
import efr.iv.igr.thriftlimit.service.ILimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Service
public class LimitServiceImpl implements ILimitService {
    private final LimitRepository limitRepository;

    private final LimitMapper limitMapper;

    private final TransactionFeignClient transactionFeignClient;

    private final ICurrencyService currencyService;

    @Autowired
    public LimitServiceImpl(LimitRepository limitRepository, TransactionFeignClient transactionFeignClient,
                            ICurrencyService currencyService, LimitMapper limitMapper) {
        this.limitRepository = limitRepository;
        this.transactionFeignClient = transactionFeignClient;
        this.currencyService = currencyService;
        this.limitMapper = limitMapper;
    }

    @Override
    public LimitResponse createLimit(LimitRequest limitRequest) {
        Limit limit = limitMapper.toEntity(limitRequest);
        limit.setLimitDatetime(Instant.now());
        return limitMapper.toResponse(limitRepository.save(limit));
    }

    @Override
    public List<LimitResponse> getLimits() {
        return limitMapper.toResponses(limitRepository.findAll());
    }

    @Override
    public List<TransactionExceededResponse> getAllTransactionsExceeded() {
        return transactionFeignClient
                .getTransaction()
                .stream()
                .filter(this::checkLimit)
                .map(x -> {
                    TransactionExceededResponse transactionExceededResponse = new TransactionExceededResponse();
                    transactionExceededResponse.setTransactionResponse(x);
                    transactionExceededResponse.setLimitResponse(
                            limitMapper.toResponse(limitRepository.findFirstByAccountIdAndCategoryOrderByIdDesc(
                                    x.getAccountFrom(),
                                    x.getCategory())));
                    transactionExceededResponse.setExceeded(true);
                    return transactionExceededResponse;
                })
                .toList();
    }

    private boolean checkLimit(TransactionResponse transactionResponse) {
        Limit limit = limitRepository.findFirstByAccountIdAndCategoryOrderByIdDesc(transactionResponse.getAccountFrom(),
                transactionResponse.getCategory());
        BigDecimal result = currencyService.convertCurrency(transactionResponse.getCurrency(),
                transactionResponse.getAmount(),
                limit.getLimitCurrency());

        return result.compareTo(limit.getSumLimit()) >= 0;
    }
}
