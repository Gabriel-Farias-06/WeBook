package com.webook.app.application.UseCase.Livro;

import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.LivroRepository;



public class CreateLivroUseCase {
    private final LivroRepository livroRepository;

    public CreateLivroUseCase(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public void execute(Livro livro){
        livroRepository.create(livro);
    }
}
