package com.webook.app.domain.Interfaces;

import com.webook.app.domain.Entity.Autor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

public interface AutorRepository  {
    Autor create(Autor autor);
    Optional<Autor> findById(UUID id);
    Optional<Autor> findByNome(String name);
    Autor update(Autor autor);
    void delete(UUID id);
}