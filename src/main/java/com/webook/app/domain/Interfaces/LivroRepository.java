package com.webook.app.domain.Interfaces;

import com.webook.app.domain.Entity.Livro;

import java.util.Optional;
import java.util.UUID;

public interface LivroRepository {
    Livro create(Livro livro);
    Optional<Livro> findById(UUID id);
    Livro update(Livro livro);
    void delete(UUID id);
}
