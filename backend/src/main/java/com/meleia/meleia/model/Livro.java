package com.meleia.meleia.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Table(name = "livro")
@Entity(name = "livro")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String isbn;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date ano_publicacao;

    private int preco;
    private String sinopse;

    @ManyToOne
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name="editora_id")
    private Editora editora;

    @ManyToMany
    @JoinTable(
            name = "livro_autor",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autores = new HashSet<>();

    public Livro(){
    }

    public Livro(String titulo, String isbn, Categoria categoria, Date ano_publicacao, int preco, Editora editora, String sinopse) {
    this.titulo = titulo;
    this.isbn = isbn;
    this.categoria = categoria;
    this.ano_publicacao = ano_publicacao;
    this.preco = preco;
    this.editora = editora;
    this.sinopse = sinopse;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getData_publicacao() {
        return ano_publicacao;
    }

    public void setData_publicacao(Date data_publicacao) {
        this.ano_publicacao = data_publicacao;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Set<Autor> getAutores() {
        return autores;
    }

    public void setAutores(Set<Autor> autores) {
        this.autores = autores;
    }


}
