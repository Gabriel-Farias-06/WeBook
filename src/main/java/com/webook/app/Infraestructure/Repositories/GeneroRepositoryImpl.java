// Infra - GeneroRepositoryImpl.java
package com.webook.app.Infraestructure.Repositories;

import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Interfaces.GeneroRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class GeneroRepositoryImpl implements GeneroRepository {

    private final GeneroJpaRepository generoJpaRepository;

    public GeneroRepositoryImpl(GeneroJpaRepository generoJpaRepository) {
        this.generoJpaRepository = generoJpaRepository;
    }

    @Override
    public Genero create(Genero genero) {
        return generoJpaRepository.save(genero);
    }

    @Override
    public Optional<Genero> findById(UUID id) {
        return generoJpaRepository.findById(id);
    }

    @Override
    public Optional<Genero> findByName(String name) {return generoJpaRepository.findByName(name);}

    @Override
    public Genero update(Genero genero) {
        return generoJpaRepository.save(genero);
    }

    @Override
    public void delete(UUID id) {
        generoJpaRepository.deleteById(id);
    }
}
