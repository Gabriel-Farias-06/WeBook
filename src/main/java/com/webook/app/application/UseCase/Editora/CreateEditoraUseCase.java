package com.webook.app.application.UseCase.Editora;

import com.webook.app.application.DTOs.EditoraDTO;
import com.webook.app.domain.Entity.Editora;
import com.webook.app.domain.Interfaces.EditoraRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateEditoraUseCase {
    private final EditoraRepository editoraRepository;

    public CreateEditoraUseCase(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    @Transactional
    public ResponseEntity<Editora> execute(Editora editora) throws IllegalArgumentException {
        Optional<Editora> editoraOptional = editoraRepository.findByNome(editora.getNome());
        if(editoraOptional.isPresent())
            return ResponseEntity.status(200).body(editoraOptional.get());
        Editora newEditora = editoraRepository.create(editora);
        return ResponseEntity.status(201).body(newEditora);
    }
}
