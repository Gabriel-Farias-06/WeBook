package com.webook.app.application.UseCase.Editora;

import com.webook.app.domain.Entity.Editora;
import com.webook.app.domain.Interfaces.EditoraRepository;

import java.util.Optional;

public class FindByNameEditoraUseCase {

    private final EditoraRepository editoraRepository;

    public FindByNameEditoraUseCase(EditoraRepository editoraRepository){
        this.editoraRepository = editoraRepository;
    }

    public Optional<Editora> execute(String name){
        return editoraRepository.findByName(name);
    }

}
