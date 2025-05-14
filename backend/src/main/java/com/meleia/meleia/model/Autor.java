package com.meleia.meleia.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table(name = "autor")
@Entity(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date aniversario;
    private String nacionalidade;


    @ManyToMany(mappedBy = "autores")
    private Set<Livro> livros = new HashSet<>();

    public Autor(String nome, Date dataNascimento, String nacionalidade) {
        this.nome = nome;
        this.aniversario = dataNascimento;
        this.nacionalidade = nacionalidade;

    }

    public Autor() {

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

    public Date getDataNascimento() {
        return aniversario;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.aniversario = dataNascimento;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public Set<Livro> getLivros() {
        return livros;
    }

    public void setLivros(Set<Livro> livros) {
        this.livros = livros;
    }
}

