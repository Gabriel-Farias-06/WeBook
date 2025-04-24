package com.webook.app.application.UseCase;

import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;

import java.util.UUID;

public class CreateUserUseCase {
    private final UsuarioRepository usuarioRepository;

    public CreateUserUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void execute(Usuario usuario) throws IllegalArgumentException {
        if(usuarioRepository.findByEmail(usuario.getEmail()).isEmpty())
            throw new IllegalArgumentException("Email já cadastrado");
        usuarioRepository.create(usuario);
    }
}
