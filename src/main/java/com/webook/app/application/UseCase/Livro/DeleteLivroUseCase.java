package com.webook.app.application.UseCase.Livro;

import com.webook.app.domain.Interfaces.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteLivroUseCase {
    private final LivroRepository livroRepository;

    public DeleteLivroUseCase(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public void execute(UUID livroId){
        if(livroRepository.findById(livroId).isEmpty())
            throw new IllegalArgumentException("Id nao cadastrado");

        livroRepository.delete(livroId);
    }
}