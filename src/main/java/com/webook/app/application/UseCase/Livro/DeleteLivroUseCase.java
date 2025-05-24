package com.webook.app.application.UseCase.Livro;

import com.webook.app.application.DTOs.Response.LivroResponse;
import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.LivroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteLivroUseCase {
    private final LivroRepository livroRepository;

    public DeleteLivroUseCase(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    @Transactional
    public ResponseEntity<Boolean> execute(String isbn){
        Optional<Livro> livro = livroRepository.findByIsbn(isbn);
        if(livro.isEmpty())
            return ResponseEntity.status(404).body(false);

        livro.get().setUsuarios(new ArrayList<>());
        livro.get().setGeneros(new ArrayList<>());
        livro.get().setAutor(null);
        livro.get().setEditora(null);

        livroRepository.update(livro.get());

        livroRepository.delete(livro.get().getLivro_id());

        return  ResponseEntity.ok(true);
    }
}