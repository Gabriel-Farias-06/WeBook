package com.webook.app.application.UseCase.Livro;

import com.webook.app.domain.Interfaces.LivroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class DeleteLivroUseCase {
    private final LivroRepository livroRepository;

    public DeleteLivroUseCase(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    @Transactional
    public void execute(UUID livroId){
        var livro = livroRepository.findById(livroId).orElseThrow(() -> new IllegalArgumentException("Id nao cadastrado"));

        livro.setUsuarios(new ArrayList<>());
        livro.setGeneros(new ArrayList<>());
        livro.setAutor(null);
        livro.setEditora(null);

        livroRepository.update(livro);

        livroRepository.delete(livroId);
    }
}