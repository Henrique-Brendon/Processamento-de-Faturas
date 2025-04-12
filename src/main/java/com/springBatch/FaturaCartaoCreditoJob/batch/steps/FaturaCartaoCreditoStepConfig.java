package com.springBatch.FaturaCartaoCreditoJob.batch.steps;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.springBatch.FaturaCartaoCreditoJob.batch.readers.FaturaCartaoCreditoReader;
import com.springBatch.FaturaCartaoCreditoJob.domain.FaturaCartaoCredito;
import com.springBatch.FaturaCartaoCreditoJob.domain.Transacao;

@Configuration
public class FaturaCartaoCreditoStepConfig {

    private final PlatformTransactionManager transactionManager;
    private final JobRepository jobRepository;

    public FaturaCartaoCreditoStepConfig(PlatformTransactionManager transactionManager, JobRepository jobRepository) {
        this.transactionManager = transactionManager;
        this.jobRepository = jobRepository;
    }

    @Bean
    Step faturaCartaoCreditoStep(
            ItemStreamReader<Transacao> lerTransacoesReader,
            ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> processarTransacoesProcessor,
            ItemWriter<FaturaCartaoCredito> escreverFaturaCartaoCredito) {
        
        return new StepBuilder("faturaCartaoCreditoStep", jobRepository)
                .<FaturaCartaoCredito, FaturaCartaoCredito>chunk(1, transactionManager)
                .reader(new FaturaCartaoCreditoReader(lerTransacoesReader))
                .processor(processarTransacoesProcessor)
                .writer(escreverFaturaCartaoCredito)
                .build();
    }
 
}
