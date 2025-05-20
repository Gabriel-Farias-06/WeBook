package com.webook.app.application.UseCase.Autor;

import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Interfaces.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateAutorUseCase {
    private final AutorRepository autorRepository;

    public UpdateAutorUseCase(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public void execute(Autor autor){
        if(autorRepository.findById(autor.getAutor_id()).isEmpty())
            throw new IllegalArgumentException("Id nao cadastrado");

        autorRepository.update(autor);
    }
}
