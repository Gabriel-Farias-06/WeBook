package com.webook.app.application.UseCase.Autor;

import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Interfaces.AutorRepository;

public class CreateAutorUseCase {

    private final AutorRepository autorRepository;

    public CreateAutorUseCase(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public void execute(Autor autor) {
        if(autorRepository.findById(autor.getAutor_id()).isPresent())
            throw new IllegalArgumentException("Gênero com mesmo nome já cadastrado");
        autorRepository.create(autor);
    }
}
