package com.webook.app.application.UseCase.Usuario;

import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindByEmailUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public FindByEmailUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> execute(String email, String senha) throws IllegalArgumentException {
        var usuarioEncontrado = usuarioRepository.findByEmail(email);
        if(usuarioEncontrado.isEmpty())
            throw new IllegalArgumentException("Usuário não encontrado / não cadastrado");
        else if(!usuarioEncontrado.get().getSenha().equals(senha))
            throw new IllegalArgumentException("Senha incorreta");
        return usuarioEncontrado;
    }
}