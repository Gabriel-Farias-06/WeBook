package com.webook.app.application.UseCase.Autor;

import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Interfaces.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindByNomeAutorUseCase {

    private final AutorRepository autorRepository;

    public FindByNomeAutorUseCase(AutorRepository autorRepository){ this.autorRepository = autorRepository; }

    public Optional<Autor> execute(String name){
        if(autorRepository.findByNome(name).isEmpty())
            throw new IllegalArgumentException("Gênero não encontrado / cadastrado");
        return autorRepository.findByNome(name);
    }
}
