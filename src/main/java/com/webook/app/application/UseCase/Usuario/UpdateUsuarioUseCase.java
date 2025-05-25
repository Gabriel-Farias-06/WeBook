package com.webook.app.application.UseCase.Usuario;

import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UpdateUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public UpdateUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void execute(Usuario usuario) throws IllegalArgumentException {
        if(usuarioRepository.findByEmail(usuario.getEmail()).isEmpty())
            throw new IllegalArgumentException("Email já cadastrado");
        usuarioRepository.update(usuario);
    }
}