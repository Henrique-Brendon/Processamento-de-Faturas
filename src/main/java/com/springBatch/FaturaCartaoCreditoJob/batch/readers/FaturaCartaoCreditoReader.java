package com.springBatch.FaturaCartaoCreditoJob.batch.readers;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.ItemStreamReader;

import com.springBatch.FaturaCartaoCreditoJob.domain.FaturaCartaoCredito;
import com.springBatch.FaturaCartaoCreditoJob.domain.Transacao;

public class FaturaCartaoCreditoReader implements ItemStreamReader<FaturaCartaoCredito> {
    private ItemStreamReader<Transacao> delegate;
    private Transacao transacaoAtual;

    public FaturaCartaoCreditoReader(ItemStreamReader<Transacao> lerTransacoesReader) {
        this.delegate = lerTransacoesReader;
    }

    @Override
    public FaturaCartaoCredito read() throws Exception {
        if(transacaoAtual == null) 
            transacaoAtual = delegate.read();

            FaturaCartaoCredito faturaCartaoCredito = null;
            Transacao transacao = transacaoAtual;
            transacaoAtual = null;

            if(transacao != null) {
                faturaCartaoCredito = new FaturaCartaoCredito();
                faturaCartaoCredito.setCartaoCredito(transacao.getCartaoCredito());
                faturaCartaoCredito.setCliente(transacao.getCartaoCredito().getCliente());
                faturaCartaoCredito.getTransacoes().add(transacao);

                while (isTransacaoRelacionada(transacao)) 
                    faturaCartaoCredito.getTransacoes().add(transacaoAtual);
                
            }
            return faturaCartaoCredito;
        }

    private boolean isTransacaoRelacionada(Transacao transacao) throws Exception {
        return peek() != null && transacao.getCartaoCredito().getNumeroCartaoCredito() == transacaoAtual.getCartaoCredito().getNumeroCartaoCredito();
    }

    private Transacao peek() throws Exception {
        transacaoAtual = delegate.read();
        return transacaoAtual;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegate.close();
    }

}
