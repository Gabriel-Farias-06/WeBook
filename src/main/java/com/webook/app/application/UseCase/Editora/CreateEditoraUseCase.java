package com.webook.app.application.UseCase.Editora;

import com.webook.app.domain.Entity.Editora;
import com.webook.app.domain.Interfaces.EditoraRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateEditoraUseCase {
    private final EditoraRepository editoraRepository;

    public CreateEditoraUseCase(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    public Editora execute(Editora editora) throws IllegalArgumentException {
        if(editoraRepository.findByNome(editora.getNome()).isPresent())
            throw new IllegalArgumentException("Editora com mesmo nome já cadastrada");
        editoraRepository.create(editora);
        return editora;
    }
}
