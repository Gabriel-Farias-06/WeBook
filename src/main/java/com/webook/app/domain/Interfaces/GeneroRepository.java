package com.webook.app.domain.Interfaces;

import com.webook.app.domain.Entity.Genero;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GeneroRepository {
    Genero create(Genero genero);
    Optional<Genero> findById(UUID id);
    Optional<Genero> findByNome(String name);
    List<Genero> findAll();
    Genero update(Genero genero);
    void delete(UUID id);
}
