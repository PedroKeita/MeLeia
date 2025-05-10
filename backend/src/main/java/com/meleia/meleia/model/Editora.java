package com.meleia.meleia.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Editora {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String email;

    @OneToMany(mappedBy = "Livro")
    private List<Livro> livros;
}
