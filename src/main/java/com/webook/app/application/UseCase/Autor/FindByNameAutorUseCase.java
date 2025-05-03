package com.webook.app.application.UseCase.Autor;

import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Interfaces.AutorRepository;

import java.util.Optional;

public class FindByNameAutorUseCase {

    private final AutorRepository autorRepository;

    public FindByNameAutorUseCase(AutorRepository autorRepository){ this.autorRepository = autorRepository; }

    public Optional<Autor> execute(String name){
        if(autorRepository.findByName(name).isEmpty())
            throw new IllegalArgumentException("Gênero não encontrado / cadastrado");
        return autorRepository.findByName(name);
    }
}
