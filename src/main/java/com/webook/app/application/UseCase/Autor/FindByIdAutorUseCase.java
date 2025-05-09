package com.webook.app.application.UseCase.Autor;

import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Interfaces.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdAutorUseCase {

    private final AutorRepository autorRepository;

    public FindByIdAutorUseCase(AutorRepository autorRepository){ this.autorRepository = autorRepository; }

    public Optional<Autor> execute(UUID id){
        if(autorRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("Gênero não cadastrado / encontrado.");
        return autorRepository.findById(id);
    }
}
