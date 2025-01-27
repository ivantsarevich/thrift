package efr.iv.igr.thriftbank.mapper;

import efr.iv.igr.thriftbank.model.entity.Transaction;
import efr.iv.igr.thriftbank.model.request.TransactionRequest;
import efr.iv.igr.thriftbank.model.response.TransactionResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transaction toEntity(TransactionRequest request);

    TransactionResponse toResponse(Transaction transaction);

    List<Transaction> toEntities(List<TransactionRequest> transactionRequests);

    List<TransactionResponse> toResponses(List<Transaction> transactions);
}
