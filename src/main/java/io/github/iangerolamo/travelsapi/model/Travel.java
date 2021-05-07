package io.github.iangerolamo.travelsapi.model;

import io.github.iangerolamo.travelsapi.enumeration.TravelTypeEnum;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Travel {

    private Long id; // número único da transação
    private String orderNumber; // número de identificação de uma viagem no sistema
    private BigDecimal amount; // valor da viagem (valor arbitrário)
    private LocalDateTime startDate; // data de início da viagem formato ISO 8601
    private LocalDateTime endDate; // data de fim da viagem no formato ISO 8601
    private TravelTypeEnum type; // viagem de ida, ida e volta ou múltiplos destinos

    public Travel(TravelTypeEnum type) {
        this.type = type;
    }
}
