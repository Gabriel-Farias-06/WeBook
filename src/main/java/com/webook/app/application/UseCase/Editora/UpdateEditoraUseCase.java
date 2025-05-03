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
        if(editoraRepository.findByName(editora.getNome()).isEmpty())
            throw new IllegalArgumentException("Livro nao cadastrado");

        editoraRepository.update(editora);
    }
}
