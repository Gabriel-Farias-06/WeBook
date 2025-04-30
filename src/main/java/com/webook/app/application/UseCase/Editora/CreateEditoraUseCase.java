package com.webook.app.application.UseCase.Editora;

import com.webook.app.domain.Entity.Editora;
import com.webook.app.domain.Interfaces.EditoraRepository;

import java.util.UUID;

public class CreateEditoraUseCase {
    private final EditoraRepository editoraRepository;

    public CreateEditoraUseCase(EditoraRepository editoraRepository) {
        this.editoraRepository = editoraRepository;
    }

    public void execute(Editora editora) throws IllegalArgumentException {
        editoraRepository.create(editora);
    }
}
