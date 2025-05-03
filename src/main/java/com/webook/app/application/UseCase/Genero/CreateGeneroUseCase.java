package com.webook.app.application.UseCase.Genero;

import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Interfaces.GeneroRepository;

public class CreateGeneroUseCase {

    private final GeneroRepository generoRepository;

    public CreateGeneroUseCase(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public void execute(Genero genero) {
        if(generoRepository.findById(genero.getGenero_id()).isPresent())
            throw new IllegalArgumentException("Gênero com mesmo ID já cadastrado");
        generoRepository.create(genero);
    }
}
