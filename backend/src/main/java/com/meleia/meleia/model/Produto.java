package com.meleia.meleia.model;


import jakarta.persistence.*;

@Table(name = "produto")
@Entity(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private int quantidade;

    public Produto(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    public Produto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }


}
