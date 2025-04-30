package com.webook.app.application.UseCase.Usuario;

import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;

import java.util.UUID;

public class DeleteUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public DeleteUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void execute(UUID id) throws IllegalArgumentException {
        var usuarioEncontrado = usuarioRepository.findById(id);
        if(usuarioEncontrado.isEmpty())
            throw new IllegalArgumentException("Usuário não existe / não encontrado");
        usuarioRepository.delete(id);
    }
}