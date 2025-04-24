package com.webook.app.Infraestructure.Repositories;

import com.webook.app.domain.Entity.Editora;
import com.webook.app.domain.Interfaces.EditoraRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class EditoraRepositoryImpl implements EditoraRepository {

    private final EditoraJpaRepository editoraJpaRepository;

    public EditoraRepositoryImpl(EditoraJpaRepository editoraJpaRepository) {
        this.editoraJpaRepository = editoraJpaRepository;
    }

    @Override
    public Editora create(Editora editora) {
        return editoraJpaRepository.save(editora);
    }

    @Override
    public Optional<Editora> findById(UUID id) {
        return editoraJpaRepository.findById(id);
    }

    @Override
    public Editora update(Editora editora) {
        return editoraJpaRepository.save(editora);
    }

    @Override
    public void delete(UUID id) {
        editoraJpaRepository.deleteById(id);
    }
}
