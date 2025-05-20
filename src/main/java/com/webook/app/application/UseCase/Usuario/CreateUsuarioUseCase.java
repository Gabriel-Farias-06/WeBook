package com.webook.app.application.UseCase.Usuario;

import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Exceptions.EmailAlreadyExistsException;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public CreateUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario execute(Usuario usuario) throws EmailAlreadyExistsException {
        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
            throw new EmailAlreadyExistsException();
        return usuarioRepository.create(usuario);
    }
}
