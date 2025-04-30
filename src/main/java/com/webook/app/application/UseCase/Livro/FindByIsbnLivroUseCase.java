package com.webook.app.application.UseCase.Livro;

import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.LivroRepository;

import java.util.Optional;
import java.util.UUID;

public class FindByIsbnLivroUseCase {
    private final LivroRepository livroRepository;

    public FindByIsbnLivroUseCase(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Optional<Livro> execute(String id) throws IllegalArgumentException {
        var livroEncontrado = livroRepository.findByIsbn(id);
        if(livroEncontrado.isEmpty())
            throw new IllegalArgumentException("Usuário não encontrado / não cadastrado");

        return livroEncontrado;
    }
}
