package com.webook.app.application.UseCase.Genero;

import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.GeneroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteGeneroUseCase {

    private final GeneroRepository generoRepository;

    public DeleteGeneroUseCase(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Transactional
    public void execute(UUID id) {
        Optional<Genero> genero = generoRepository.findById(id);

        if(genero.isEmpty())
            throw new IllegalArgumentException("Livro com esse ID não foi encontrado");

        for (Livro livro : genero.get().getLivros()) {
            livro.getGeneros().remove(genero.get());
        }

        genero.get().getLivros().clear();
        generoRepository.delete(id);
    }
}
