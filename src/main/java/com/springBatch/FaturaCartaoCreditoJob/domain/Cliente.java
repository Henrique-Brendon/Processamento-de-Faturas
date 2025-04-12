package com.springBatch.FaturaCartaoCreditoJob.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Getter
public class Cliente {
    private int id;
    private String nome;
    private String endereco;
}
