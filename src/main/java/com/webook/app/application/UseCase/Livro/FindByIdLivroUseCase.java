package com.webook.app.application.UseCase.Livro;

import com.webook.app.application.DTOs.Response.LivroResponse;
import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.LivroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdLivroUseCase {
    private final LivroRepository livroRepository;

    public FindByIdLivroUseCase(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public ResponseEntity<LivroResponse> execute(UUID id) {
        Optional<Livro> livro = livroRepository.findById(id);
        return livro.map(value -> ResponseEntity.ok(new LivroResponse(value))).orElseGet(() -> ResponseEntity.status(404).body(null));
    }
}
