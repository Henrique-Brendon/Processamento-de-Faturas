package com.springBatch.FaturaCartaoCreditoJob.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Getter
public class CartaoCredito {
    private int numeroCartaoCredito;
    private Cliente cliente;

}
