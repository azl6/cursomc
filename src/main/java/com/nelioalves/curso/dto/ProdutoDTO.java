package com.nelioalves.curso.dto;

import java.io.Serializable;

public class ProdutoDTO implements Serializable {
    private Integer id;
    private String nome;
    private Double preço;

    public ProdutoDTO(){
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreço() {
        return preço;
    }

    public void setPreço(Double preço) {
        this.preço = preço;
    }
}
