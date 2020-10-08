package com.batch.EnvioEmailJob.step;

import com.batch.EnvioEmailJob.dominio.InteresseProdutoCliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class EnvioEmailClientesStep {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public Step enviaEmailClientesStep(
            ItemReader<InteresseProdutoCliente> lerInteresseProutoClienteReader,
            ItemProcessor<InteresseProdutoCliente, SimpleMailMessage> processarEmailProdutoCliente,
            ItemWriter<SimpleMailMessage> enviarEmailProdutoClienteWriter) {
        return stepBuilderFactory
                .get("enviaEmailClientesStep")
                .<InteresseProdutoCliente, SimpleMailMessage>chunk(1)
                .reader(lerInteresseProutoClienteReader)
                .processor(processarEmailProdutoCliente)
                .writer(enviarEmailProdutoClienteWriter)
                .build();
    }
}
