package efr.iv.igr.thriftlimit.repository;

import efr.iv.igr.thriftlimit.model.entity.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LimitRepository extends JpaRepository<Limit, Long> {
}
