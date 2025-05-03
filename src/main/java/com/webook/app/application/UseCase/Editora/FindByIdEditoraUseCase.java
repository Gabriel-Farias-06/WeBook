package com.webook.app.application.UseCase.Editora;

import com.webook.app.domain.Entity.Editora;
import com.webook.app.domain.Interfaces.EditoraRepository;

import java.util.Optional;
import java.util.UUID;

public class FindByIdEditoraUseCase {

    private final EditoraRepository editoraRepository;

    public FindByIdEditoraUseCase(EditoraRepository editoraRepository){this.editoraRepository = editoraRepository;}

    public Optional<Editora> execute(UUID id){
        if(editoraRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("ID não cadastrado");

        return editoraRepository.findById(id);
    }
}
