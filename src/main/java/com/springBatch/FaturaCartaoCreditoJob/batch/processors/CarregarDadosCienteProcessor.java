package com.springBatch.FaturaCartaoCreditoJob.batch.processors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.springBatch.FaturaCartaoCreditoJob.domain.Cliente;
import com.springBatch.FaturaCartaoCreditoJob.domain.FaturaCartaoCredito;

@Component
public class CarregarDadosCienteProcessor implements ItemProcessor<FaturaCartaoCredito, FaturaCartaoCredito> {
    
    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public FaturaCartaoCredito process(FaturaCartaoCredito faturaCartaoCredito) throws Exception {
        String uri = String.format("http://my-json-server.typicode.com/giuliana-bezerra/demo/profile/%d", 
            faturaCartaoCredito.getCliente().getId());

        ResponseEntity<Cliente> response = restTemplate.getForEntity(uri, Cliente.class);

        if (response.getStatusCode() != HttpStatus.OK) {
            throw new ValidationException("Cliente não encontrado!");
        }

        faturaCartaoCredito.setCliente(response.getBody());
        return faturaCartaoCredito;
    }
}
