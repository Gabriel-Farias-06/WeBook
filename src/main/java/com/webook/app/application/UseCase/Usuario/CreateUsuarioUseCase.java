package com.webook.app.application.UseCase.Usuario;

import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;

import java.util.UUID;

public class CreateUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public CreateUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void execute(Usuario usuario) throws IllegalArgumentException {
        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
            throw new IllegalArgumentException("Email já cadastrado");
        usuarioRepository.create(usuario);
    }
}
