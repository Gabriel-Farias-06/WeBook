package com.webook.app.application.UseCase.Livro;

import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.LivroRepository;

import java.util.Optional;
import java.util.UUID;

public class FindByIdLIvroUseCase {
    private final LivroRepository livroRepository;

    public FindByIdLIvroUseCase(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Optional<Livro> execute(UUID id) throws IllegalArgumentException {
        var livroEncontrado = livroRepository.findById(id);
        if(livroEncontrado.isEmpty())
            throw new IllegalArgumentException("Usuário não encontrado / não cadastrado");
        return livroEncontrado;
    }
}
