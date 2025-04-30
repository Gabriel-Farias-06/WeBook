package com.webook.app.application.UseCase.Livro;

import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.LivroRepository;

import java.util.UUID;

public class UpdateLivroUseCase {
    private final LivroRepository livroRepository;

    public UpdateLivroUseCase(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public void execute(Livro livro){
        if(livroRepository.findById(livro.getLivro_id()).isEmpty())
            throw new IllegalArgumentException("Id nao cadastrado");

        livroRepository.update(livro);
    }
}
