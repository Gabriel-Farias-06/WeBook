package com.webook.app.application.UseCase.Editora;

import com.webook.app.domain.Entity.Editora;
import com.webook.app.domain.Interfaces.EditoraRepository;
import com.webook.app.domain.Interfaces.LivroRepository;

public class UpdateEditoraUseCase {
    private final EditoraRepository editoraRepository;

    public UpdateEditoraUseCase(EditoraRepository editoraRepository){
        this.editoraRepository = editoraRepository;
    }

    public void execute(Editora editora){
        if(editoraRepository.findById(editora.getEditora_id()).isEmpty())
            throw new IllegalArgumentException("Id nao cadastrado");

        editoraRepository.update(editora);
    }
}
