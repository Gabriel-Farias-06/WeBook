package com.webook.app.Infraestructure.Repositories;

import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.LivroRepository;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class LivroRepositoryImpl implements LivroRepository {

    private LivroJpaRepository livroJpaRepository;

    @Override
    public Livro create(Livro livro) {
        return livroJpaRepository.save(livro);
    }

    @Override
    public Optional<Livro> findById(UUID id) {
        return livroJpaRepository.findById(id);
    }

    @Override
    public Livro update(Livro livro) {
        return livroJpaRepository.save(livro);
    }

    @Override
    public void delete(UUID id) {
        livroJpaRepository.deleteById(id);
    }
}
