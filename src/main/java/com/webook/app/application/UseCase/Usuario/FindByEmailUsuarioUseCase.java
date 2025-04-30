package com.webook.app.application.UseCase.Usuario;

import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;

import java.util.Optional;

public class FindByEmailUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public FindByEmailUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> execute(String email) throws IllegalArgumentException {
        var usuarioEncontrado = usuarioRepository.findByEmail(email);
        if(usuarioEncontrado.isEmpty())
            throw new IllegalArgumentException("Usuário não encontrado / não cadastrado");
        return usuarioEncontrado;
    }
}