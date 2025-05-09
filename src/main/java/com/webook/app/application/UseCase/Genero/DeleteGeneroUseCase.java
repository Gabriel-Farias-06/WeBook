package com.webook.app.application.UseCase.Genero;

import com.webook.app.domain.Interfaces.GeneroRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteGeneroUseCase {

    private final GeneroRepository generoRepository;

    public DeleteGeneroUseCase(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public void execute(UUID id) {
        if(generoRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("Livro com esse ID não foi encontrado");
        generoRepository.delete(id);
    }
}
