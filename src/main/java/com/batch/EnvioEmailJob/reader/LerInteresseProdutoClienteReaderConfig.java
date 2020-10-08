package com.batch.EnvioEmailJob.reader;

import com.batch.EnvioEmailJob.dominio.Cliente;
import com.batch.EnvioEmailJob.dominio.InteresseProdutoCliente;
import com.batch.EnvioEmailJob.dominio.Produto;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Configuration
public class LerInteresseProdutoClienteReaderConfig {

    @Bean
    public JdbcCursorItemReader<InteresseProdutoCliente> lerInteresseProdutoClienteReader(
            @Qualifier("appDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<InteresseProdutoCliente>()
                .name("lerInteresseProdutoClienteReader")
                .dataSource(dataSource)
                .sql("select * from interesse_produto_cliente " +
                        "join cliente on (cliente = cliente.id) " +
                        "join produto on (produto = produto.id)")
                .rowMapper(rowMapper())
                .build();
    }

    private RowMapper<InteresseProdutoCliente> rowMapper() {
        return new RowMapper<InteresseProdutoCliente>() {
            @Override
            public InteresseProdutoCliente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt(("id")));
                cliente.setNome(resultSet.getString(("nome")));
                cliente.setEmail(resultSet.getString(("email")));

                Produto produto = new Produto();
                produto.setId(resultSet.getInt(6));
                produto.setNome(resultSet.getString(7));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));

                InteresseProdutoCliente interesseProdutoCliente = new InteresseProdutoCliente();
                interesseProdutoCliente.setCliente(cliente);
                interesseProdutoCliente.setProduto(produto);

                return interesseProdutoCliente;
            }
        };
    }


}
