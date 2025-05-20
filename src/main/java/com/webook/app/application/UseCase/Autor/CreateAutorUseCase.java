package com.webook.app.application.UseCase.Autor;

import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Interfaces.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateAutorUseCase {

    private final AutorRepository autorRepository;

    public CreateAutorUseCase(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public Autor execute(Autor autor) {
        autorRepository.create(autor);
        return autor;
    }
}
