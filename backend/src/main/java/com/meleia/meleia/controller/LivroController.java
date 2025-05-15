package com.meleia.meleia.controller;

import com.meleia.meleia.model.Livro;
import com.meleia.meleia.repositories.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("livro")
public class LivroController {


    private final LivroRepository livroRepository;

    @Autowired
    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @GetMapping
    public ResponseEntity listarLivros(){
        return ResponseEntity.ok(livroRepository.findAll());
    }


    @PostMapping
    public ResponseEntity<Livro> criarLivro(@RequestBody @Valid Livro request){
        Livro livro = new  Livro(request.getTitulo(), request.getIsbn(), request.getCategoria(),
                request.getData_publicacao(), request.getPreco(), request.getEditora(), request.getSinopse());
        return ResponseEntity.ok(livroRepository.save(livro));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Livro> updateLivro(@PathVariable Long id, @RequestBody @Valid Livro request_update){
        return livroRepository.findById(id)
                .map(livro -> {
                    livro.setTitulo(request_update.getTitulo());
                    livro.setIsbn(request_update.getIsbn());
                    livro.setCategoria(request_update.getCategoria());
                    livro.setData_publicacao(request_update.getData_publicacao());
                    livro.setPreco(request_update.getPreco());
                    livro.setEditora(request_update.getEditora());
                    livro.setSinopse(request_update.getSinopse());
                    return ResponseEntity.ok(livroRepository.save(livro));
                })
                .orElse(ResponseEntity.notFound().build());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteLivro(@PathVariable Long id){
        return livroRepository.findById(id)
                .map(produto-> {
                    livroRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}



