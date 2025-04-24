package com.webook.app.Infraestructure.Repositories;

import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;

import java.util.Optional;
import java.util.UUID;

public class UsuarioRepositoryImpl implements UsuarioRepository {
    private final UsuarioJpaRepository usuarioJpaRepository;

    public UsuarioRepositoryImpl(UsuarioJpaRepository usuarioJpaRepository){
        this.usuarioJpaRepository = usuarioJpaRepository;
    }

    @Override
    public Usuario create(Usuario usuario) {
        return usuarioJpaRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> findById(UUID id) {
        return usuarioJpaRepository.findById(id);
    }

    @Override
    public Usuario update(Usuario usuario) {
        return usuarioJpaRepository.save(usuario);
    }

    @Override
    public void delete(UUID id) {
        usuarioJpaRepository.deleteById(id);
    }
}
