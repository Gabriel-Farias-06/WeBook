package com.webook.app.application.UseCase.Genero;

import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Interfaces.GeneroRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateGeneroUseCase {

    private final GeneroRepository generoRepository;

    public CreateGeneroUseCase(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public Genero execute(Genero genero) {
        if(generoRepository.findByNome(genero.getNome()).isPresent())
            throw new IllegalArgumentException("Gênero com mesmo nome já cadastrado");
        return generoRepository.create(genero);
    }
}
