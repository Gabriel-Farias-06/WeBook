package com.webook.app.application.UseCase.Usuario;

import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class DeleteUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public DeleteUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public void execute(UUID id) throws IllegalArgumentException {
        var usuarioEncontrado = usuarioRepository.findById(id);
        if(usuarioEncontrado.isEmpty())
            throw new IllegalArgumentException("Usuário não existe / não encontrado");
        usuarioEncontrado.get().setLivros(new ArrayList<>());
        usuarioRepository.update(usuarioEncontrado.get());

        usuarioRepository.delete(id);
    }
}