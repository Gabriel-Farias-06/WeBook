package com.webook.app.domain.Interfaces;

import com.webook.app.domain.Entity.Usuario;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository {
    Usuario create(Usuario usuario);
    Optional<Usuario> findById(UUID id);
    Usuario update(Usuario usuario);
    void delete(UUID id);
}
