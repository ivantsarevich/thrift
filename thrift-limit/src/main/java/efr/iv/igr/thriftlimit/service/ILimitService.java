package efr.iv.igr.thriftlimit.service;

import efr.iv.igr.thriftlimit.model.entity.Limit;

import java.util.List;

public interface ILimitService {
    Limit createLimit(Limit limit);

    List<Limit> getLimits();
}
