package com.webook.app.domain.Interfaces;

import com.webook.app.domain.Entity.Editora;

import java.util.Optional;
import java.util.UUID;

public interface EditoraRepository {
    Editora create(Editora editora);
    Optional<Editora> findById(UUID id);
    Editora update(Editora editora);
    void delete(UUID id);
}
