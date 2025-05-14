package com.meleia.meleia.controller;

import com.meleia.meleia.model.Produto;
import com.meleia.meleia.repositories.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
        Produto produtos = new Produto(request.getNome(), request.getQuantidade());

        this.produtoRepository.save(produtos);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping
    public ResponseEntity getAllProducts() {
        return ResponseEntity.ok(this.produtoRepository.findAll());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Produto> update(@PathVariable Long id, @RequestBody @Valid Produto request_update){
        return produtoRepository.findById(id)
                .map(produto -> {
                    produto.setNome(request_update.getNome());
                    produto.setQuantidade(request_update.getQuantidade());
                    return ResponseEntity.ok(produtoRepository.save(produto));
                })
                .orElse(ResponseEntity.notFound().build());

    }

    //Revisar dps
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        return produtoRepository.findById(id)
                .map(produto-> {
                    produtoRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
