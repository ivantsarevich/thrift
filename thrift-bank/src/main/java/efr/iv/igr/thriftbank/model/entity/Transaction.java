package efr.iv.igr.thriftbank.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountFrom;

    private Long accountTo;

    @Enumerated(EnumType.STRING)
    private CurrencyCode currency;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Instant date;
}
