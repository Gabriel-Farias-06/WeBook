package com.webook.app.application.UseCase.Editora;

import com.webook.app.domain.Interfaces.EditoraRepository;

import java.util.UUID;

public class DeleteEditoraUseCase {
    private final EditoraRepository editoraRepository;

    public DeleteEditoraUseCase(EditoraRepository editoraRepository){
        this.editoraRepository = editoraRepository;
    }

    public void execute(UUID id){
        if(editoraRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("Id não cadastrado");

        editoraRepository.delete(id);
    }
}
