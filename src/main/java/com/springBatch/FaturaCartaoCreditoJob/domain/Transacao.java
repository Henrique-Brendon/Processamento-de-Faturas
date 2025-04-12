package com.springBatch.FaturaCartaoCreditoJob.domain;

import java.sql.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Setter
@Getter
public class Transacao {
    private int id;
    private CartaoCredito cartaoCredito;
    private String descricao;
    private double valor;
    private Date data;
}
