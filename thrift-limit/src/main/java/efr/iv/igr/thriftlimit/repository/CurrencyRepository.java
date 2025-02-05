package efr.iv.igr.thriftlimit.repository;

import efr.iv.igr.thriftlimit.model.entity.Currency;
import efr.iv.igr.thriftlimit.model.entity.CurrencyCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    Currency findFirstByCodeOrderByIdDesc(CurrencyCode code);
}
