package com.webook.app.application.UseCase.Livro;

import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdLivroUseCase {
    private final LivroRepository livroRepository;

    public FindByIdLivroUseCase(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Optional<Livro> execute(UUID id) throws IllegalArgumentException {
        return livroRepository.findById(id);
    }
}
