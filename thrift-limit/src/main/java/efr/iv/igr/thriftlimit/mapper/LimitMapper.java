package efr.iv.igr.thriftlimit.mapper;

import efr.iv.igr.thriftlimit.model.entity.Limit;
import efr.iv.igr.thriftlimit.model.request.LimitRequest;
import efr.iv.igr.thriftlimit.model.response.LimitResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LimitMapper {
    Limit toEntity(LimitRequest request);

    LimitResponse toResponse(Limit limit);

    List<Limit> toEntities(List<LimitRequest> requests);

    List<LimitResponse> toResponses(List<Limit> limits);
}
