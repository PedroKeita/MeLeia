package com.meleia.meleia.controller;

import com.meleia.meleia.model.Editora;
import com.meleia.meleia.repositories.EditoraRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("editora")
public class EditoraController {

    private final EditoraRepository editoraRepository;

    @Autowired
    public EditoraController(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    @GetMapping
    public ResponseEntity findall() {
        return ResponseEntity.ok(editoraRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Editora> criar(@RequestBody @Valid Editora request) {
        Editora editora = new Editora(request.getNome(), request.getTelefone(), request.getEmail());
        return ResponseEntity.ok(editoraRepository.save(editora));

    }
}
