package com.webook.app.Infraestructure.Repositories;

import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;

import java.util.Optional;
import java.util.UUID;

public class UsuarioRepositoryImpl implements UsuarioRepository {

    @Override
    public Usuario create(Usuario usuario) {
        return null;
    }

    @Override
    public Optional<Usuario> findById(UUID id) {
        return null;
    }

    @Override
    public Usuario update(Usuario usuario) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }
}
