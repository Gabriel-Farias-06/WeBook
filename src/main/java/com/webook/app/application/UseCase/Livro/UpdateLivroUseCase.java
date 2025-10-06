package com.webook.app.application.UseCase.Livro;

import com.webook.app.application.DTOs.Request.LivroRequest;
import com.webook.app.application.DTOs.Response.LivroResponse;
import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.LivroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateLivroUseCase {
    private final LivroRepository livroRepository;

    public UpdateLivroUseCase(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public ResponseEntity<LivroResponse> execute(LivroRequest livroRequest){
        Optional<Livro> livro = livroRepository.findByIsbn(livroRequest.getIsbn());
        return livro.map(value -> ResponseEntity.status(201).body(new LivroResponse(livroRepository.update(value)))).orElseGet(() -> ResponseEntity.status(404).body(null));

    }
}
