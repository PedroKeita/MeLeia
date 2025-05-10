package com.meleia.meleia.controller;

import com.meleia.meleia.model.Produto;
import com.meleia.meleia.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produto")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    @Autowired
    public ProdutoController(ProdutoRepository produtoRepository, ProdutoRepository produtoRepository1) {
        this.produtoRepository = produtoRepository1;
    }

    @PostMapping
    public ResponseEntity<Produto> save(@RequestBody @Valid Produto request){
        Produto produto = new Produto(request.getNome(), request.getQuantidade());

        this.produtoRepository.save(produto);
        return ResponseEntity.ok(produto);
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(this.produtoRepository.findAll());
    }
}
