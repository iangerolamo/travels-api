package io.github.iangerolamo.travelsapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Statistic {

    private BigDecimal sum; // soma total das viagens criadas
    private BigDecimal avg; // média dos valores das viagens criadas
    private BigDecimal max; // maior valor dentre as viagens criadas
    private BigDecimal min; // menor valor dentre as viagens criadas
    private long count; // número total de viagens
}
