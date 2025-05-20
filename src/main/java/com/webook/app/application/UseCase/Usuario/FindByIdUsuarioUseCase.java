package com.webook.app.application.UseCase.Usuario;

import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public FindByIdUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> execute(UUID id) throws IllegalArgumentException {
        var usuarioEncontrado = usuarioRepository.findById(id);
        if(usuarioEncontrado.isEmpty())
            throw new IllegalArgumentException("Usuário não encontrado / não cadastrado");
        return usuarioEncontrado;
    }
}