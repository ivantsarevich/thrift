package efr.iv.igr.thriftlimit.service.impl;

import efr.iv.igr.thriftlimit.model.entity.Limit;
import efr.iv.igr.thriftlimit.repository.LimitRepository;
import efr.iv.igr.thriftlimit.service.ILimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LimitServiceImpl implements ILimitService {
    private final LimitRepository limitRepository;

    @Autowired
    public LimitServiceImpl(LimitRepository limitRepository) {
        this.limitRepository = limitRepository;
    }

    @Override
    public Limit createLimit(Limit limit) {
        limit.setLimitDatetime(Instant.now());
        return limitRepository.save(limit);
    }

    @Override
    public List<Limit> getLimits() {
        return limitRepository.findAll();
    }
}
