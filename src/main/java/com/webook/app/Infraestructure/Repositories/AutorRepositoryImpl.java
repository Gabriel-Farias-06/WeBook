package com.webook.app.Infraestructure.Repositories;

import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Interfaces.AutorRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class AutorRepositoryImpl implements AutorRepository {

    private final AutorJpaRepository autorJpaRepository;

    public AutorRepositoryImpl(AutorJpaRepository autorJpaRepository) {
        this.autorJpaRepository = autorJpaRepository;
    }

    @Override
    public Autor create(Autor autor) {
        return autorJpaRepository.save(autor);
    }

    @Override
    public Optional<Autor> findById(UUID id) {
        return autorJpaRepository.findById(id);
    }

    @Override
    public Optional<Autor> findByNomeAndSobrenome(String nome, String sobrenome) { return autorJpaRepository.findByNomeAndSobrenome(nome, sobrenome); }

    @Override
    public Autor update(Autor autor) {
        return autorJpaRepository.save(autor);
    }

    @Override
    public void delete(UUID id) {
        autorJpaRepository.deleteById(id);
    }
}
