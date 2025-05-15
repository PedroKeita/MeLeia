package com.meleia.meleia.controller;

import com.meleia.meleia.model.user.AuthenticationDTO;
import com.meleia.meleia.model.user.LoginResponseDTO;
import com.meleia.meleia.model.user.RegisterDTO;
import com.meleia.meleia.model.user.Usuario;
import com.meleia.meleia.repositories.UsuarioRepository;
import com.meleia.meleia.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private TokenService tokenService;


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.senha());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/registro")
    public ResponseEntity registro(@RequestBody @Valid RegisterDTO data){

        if(this.usuarioRepository.findByLogin(data.login())!=null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Usuario novoUsuario = new Usuario(data.login(), encryptedPassword, data.role());


        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }
}
