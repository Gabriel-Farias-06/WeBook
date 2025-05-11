package com.webook.app.application.UseCase.Editora;

import com.webook.app.domain.Interfaces.EditoraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeleteEditoraUseCase {
    private final EditoraRepository editoraRepository;

    public DeleteEditoraUseCase(EditoraRepository editoraRepository){
        this.editoraRepository = editoraRepository;
    }

    @Transactional
    public void execute(UUID id){
        if(editoraRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("Id não cadastrado");

        editoraRepository.delete(id);
    }
}
