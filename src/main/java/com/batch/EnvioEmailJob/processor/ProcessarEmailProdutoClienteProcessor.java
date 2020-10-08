package com.batch.EnvioEmailJob.processor;

import com.batch.EnvioEmailJob.dominio.InteresseProdutoCliente;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;

@Component
public class ProcessarEmailProdutoClienteProcessor implements ItemProcessor<InteresseProdutoCliente, SimpleMailMessage> {

    @Override
    public SimpleMailMessage process(InteresseProdutoCliente interesseProdutoCliente) throws Exception {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom("xpto@no-reply.com");
        email.setTo(interesseProdutoCliente.getCliente().getEmail());
        email.setSubject("Promoção imperdível");
        email.setText(gerarTextoPromocao(interesseProdutoCliente));
        Thread.sleep(2000); // MailTrap envio de emails em curto periodo de tempo
        return email;
    }

    private String gerarTextoPromocao(InteresseProdutoCliente interesseProdutoCliente) {
        StringBuilder writer = new StringBuilder();
        writer.append(String.format("Olá, %s\n\n", interesseProdutoCliente.getCliente().getNome()));
        writer.append("Essa promoção pode ser do seu interesse: \n\n");
        writer.append(String.format("%s - %s\n\n", interesseProdutoCliente.getProduto().getNome(), interesseProdutoCliente.getProduto().getDescricao()));
        writer.append(String.format("Por apenas: , %s!", NumberFormat.getCurrencyInstance().format(interesseProdutoCliente.getProduto().getPreco())));
        return writer.toString();
    }

}
