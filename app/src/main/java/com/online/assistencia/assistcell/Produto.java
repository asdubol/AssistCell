package com.online.assistencia.assistcell;

public class Produto {

    private String nome;
    private Double valor;

    public Produto(String nome, Double valor) {
        super();
        this.nome = nome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

}