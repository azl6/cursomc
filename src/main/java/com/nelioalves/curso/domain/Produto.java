package com.nelioalves.curso.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
public class Produto implements Serializable {

    //continuar 10min
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Double preço;

    private Set<ItemPedido> itens = new HashSet<>();

    //aula 18
    @JsonBackReference
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "PRODUTO_CATEGORIA",
    joinColumns = @JoinColumn(name = "produto_id"),//esse nome eh o nome lá na tabela da coluna
            inverseJoinColumns = @JoinColumn(name = "categoria_id")
    )
    private List<Categoria> categorias = new ArrayList<>();


    public List<Pedido> getPedidos(){
        List<Pedido> lista = new ArrayList<>();

        for (ItemPedido x : itens){
            lista.add(x.getPedido());
        }
        return lista;
    }

    public Produto(){
    }

    public Produto(Integer id, String nome, Double preço) {
        this.id = id;
        this.nome = nome;
        this.preço = preço;
    }

    public Produto(String nome, Double preço) {
        this.nome = nome;
        this.preço = preço;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
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

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }
}
