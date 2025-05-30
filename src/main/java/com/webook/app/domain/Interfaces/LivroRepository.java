package com.webook.app.domain.Interfaces;

import com.webook.app.domain.Entity.Livro;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LivroRepository {
    Livro create(Livro livro);
    Optional<Livro> findById(UUID id);
    Optional<Livro> findByIsbn(String isbn);
    List<Livro> findAll();
    Livro update(Livro livro);
    void delete(UUID id);
}
