package com.webook.app.application.UseCase.Genero;

import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Interfaces.GeneroRepository;

import java.util.Optional;

public class FindByNameGeneroUseCase {

    private final GeneroRepository generoRepository;

    public FindByNameGeneroUseCase(GeneroRepository generoRepository){ this.generoRepository = generoRepository; }

    public Optional<Genero> execute(String name){
        if(generoRepository.findByName(name).isEmpty())
            throw new IllegalArgumentException("Gênero não encontrado / cadastrado");
        return generoRepository.findByName(name);
    }
}
