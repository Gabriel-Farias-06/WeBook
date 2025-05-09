package com.webook.app.application.UseCase.Genero;

import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Interfaces.GeneroRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindByNomeGeneroUseCase {

    private final GeneroRepository generoRepository;

    public FindByNomeGeneroUseCase(GeneroRepository generoRepository){ this.generoRepository = generoRepository; }

    public Optional<Genero> execute(String name){
        if(generoRepository.findByNome(name).isEmpty())
            throw new IllegalArgumentException("Gênero não encontrado / cadastrado");
        return generoRepository.findByNome(name);
    }
}
