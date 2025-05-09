package com.webook.app.application.UseCase.Editora;

import com.webook.app.domain.Entity.Editora;
import com.webook.app.domain.Interfaces.EditoraRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindByNomeEditoraUseCase {

    private final EditoraRepository editoraRepository;

    public FindByNomeEditoraUseCase(EditoraRepository editoraRepository){
        this.editoraRepository = editoraRepository;
    }

    public Optional<Editora> execute(String name){
        return editoraRepository.findByNome(name);
    }

}
