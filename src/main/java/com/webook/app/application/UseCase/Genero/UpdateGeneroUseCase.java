package com.webook.app.application.UseCase.Genero;

import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Interfaces.GeneroRepository;

public class UpdateGeneroUseCase {
    private final GeneroRepository generoRepository;

    public UpdateGeneroUseCase(GeneroRepository generoRepository){
        this.generoRepository = generoRepository;
    }

    public void execute(Genero genero){
        if(generoRepository.findById(genero.getGenero_id()).isEmpty())
            throw new IllegalArgumentException("Id nao cadastrado");

        generoRepository.update(genero);
    }
}
