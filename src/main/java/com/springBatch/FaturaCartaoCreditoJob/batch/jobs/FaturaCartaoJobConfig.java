package com.springBatch.FaturaCartaoCreditoJob.batch.jobs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FaturaCartaoJobConfig {

    private final JobRepository jobRepository;

    public FaturaCartaoJobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    Job faturaCartaoJob(Step faturaCartaoCreditoStep) {
        return new JobBuilder("faturaCartaoJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(faturaCartaoCreditoStep)
                .build();
    }
}