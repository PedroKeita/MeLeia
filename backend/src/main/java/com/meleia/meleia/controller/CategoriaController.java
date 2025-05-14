package com.meleia.meleia.controller;

import com.meleia.meleia.model.Categoria;
import com.meleia.meleia.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("categoria")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public ResponseEntity findall(){
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Categoria> criar_categoria(@RequestBody @Valid Categoria request){
        Categoria categoria = new Categoria(request.getNome(),request.getDescricao());
        return ResponseEntity.ok(categoriaRepository.save(categoria));
    }
}
