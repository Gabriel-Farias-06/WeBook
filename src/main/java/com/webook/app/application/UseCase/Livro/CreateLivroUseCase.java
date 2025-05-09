package com.webook.app.application.UseCase.Livro;

import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateLivroUseCase {
    private final LivroRepository livroRepository;

    public CreateLivroUseCase(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public Livro execute(Livro livro){
        if(livroRepository.findByIsbn(livro.getIsbn()).isPresent())
            throw new IllegalArgumentException("Livro com mesmo ISBN já cadastrado");
        livroRepository.create(livro);
        return livro;
    }
}
