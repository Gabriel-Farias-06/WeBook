package com.webook.app.application.UseCase.Genero;

import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Interfaces.GeneroRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdGeneroUseCase {

    private final GeneroRepository generoRepository;

    public FindByIdGeneroUseCase(GeneroRepository generoRepository){ this.generoRepository = generoRepository; }

    public Optional<Genero> execute(UUID id){
        if(generoRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("Gênero não cadastrado / encontrado.");
        return generoRepository.findById(id);
    }
}
