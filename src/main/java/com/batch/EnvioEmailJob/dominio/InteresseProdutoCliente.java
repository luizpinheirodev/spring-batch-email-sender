package com.batch.EnvioEmailJob.dominio;

public class InteresseProdutoCliente {

    private Cliente cliente;
    private Produto produto;

    public InteresseProdutoCliente() {
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
