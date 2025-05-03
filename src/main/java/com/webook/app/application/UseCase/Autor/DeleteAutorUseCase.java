package com.webook.app.application.UseCase.Autor;

import com.webook.app.domain.Interfaces.AutorRepository;

import java.util.UUID;

public class DeleteAutorUseCase {

    private final AutorRepository autorRepository;

    public DeleteAutorUseCase(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void execute(UUID id) {
        if(autorRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("Autor com esse ID não foi encontrado");
        autorRepository.delete(id);
    }
}
