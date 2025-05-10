package com.meleia.meleia.model.user;

public record RegisterDTO(String login, String senha, UsuarioRole role) {
}
