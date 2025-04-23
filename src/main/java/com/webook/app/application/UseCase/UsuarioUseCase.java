package com.webook.app.application.UseCase;

import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;

import java.util.Optional;
import java.util.UUID;

public class UsuarioUseCase  {
    private UsuarioRepository usuarioRepository;

    Usuario cadastrarUsuario(Usuario usuario) {
        return usuarioRepository.create(usuario);
    }

    Optional<Usuario> encontrarPorId(UUID id) {
        return  usuarioRepository.findById(id);
    }

    Usuario atualizarUsuario(Usuario usuario) {
        return usuarioRepository.update(usuario);
    }

    void deletarUsuario(UUID id) {
        usuarioRepository.delete(id);
    }

}
