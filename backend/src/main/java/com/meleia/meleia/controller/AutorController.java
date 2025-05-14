package com.meleia.meleia.controller;

import com.meleia.meleia.model.Autor;
import com.meleia.meleia.model.Livro;
import com.meleia.meleia.repositories.AutorRepository;
import com.meleia.meleia.repositories.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("autor")
public class AutorController {

    private final AutorRepository autorRepository;

    @Autowired
    public AutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @GetMapping
    public ResponseEntity findAll(){
        return ResponseEntity.ok(autorRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Autor> findAll(@RequestBody @Valid Autor request){
        Autor autor = new Autor(request.getNome(), request.getDataNascimento(), request.getNacionalidade());
        return ResponseEntity.ok(autorRepository.save(autor));
    }



}
