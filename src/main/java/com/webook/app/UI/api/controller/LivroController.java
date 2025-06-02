package com.webook.app.UI.api.controller;

import com.webook.app.application.DTOs.LivroDTO;
import com.webook.app.application.DTOs.Request.LivroRequest;
import com.webook.app.application.DTOs.Response.LivroResponse;
import com.webook.app.application.UseCase.Livro.*;
import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Entity.Editora;
import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Enums.ClassificacaoIndicativa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/livro")
public class LivroController {
    private final CreateLivroUseCase createLivroUseCase;
    private final UpdateLivroUseCase updateLivroUseCase;
    private final DeleteLivroUseCase deleteLivroUseCase;
    private final FindByIdLivroUseCase findByIdLivroUseCase;
    private final FindByIsbnLivroUseCase findByIsbnLivroUseCase;
    private final FindAllLivroUseCase findAllLivroUseCase;

    public LivroController(CreateLivroUseCase createLivroUseCase, UpdateLivroUseCase updateLivroUseCase, DeleteLivroUseCase deleteLivroUseCase, FindByIdLivroUseCase findByIdLivroUseCase, FindByIsbnLivroUseCase findByIsbnLivroUseCase, FindAllLivroUseCase findAllLivroUseCase) {
        this.createLivroUseCase = createLivroUseCase;
        this.updateLivroUseCase = updateLivroUseCase;
        this.deleteLivroUseCase = deleteLivroUseCase;
        this.findByIdLivroUseCase = findByIdLivroUseCase;
        this.findByIsbnLivroUseCase = findByIsbnLivroUseCase;
        this.findAllLivroUseCase = findAllLivroUseCase;
    }

    @PostMapping
    public ResponseEntity<LivroResponse> create(@RequestBody LivroRequest livroRequest) {
        return createLivroUseCase.execute(livroRequest);
    }

    @PutMapping
    public ResponseEntity<LivroResponse> update(@RequestBody LivroRequest livroRequest) {
        return updateLivroUseCase.execute(livroRequest);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Boolean> delete(@PathVariable String isbn) {
        deleteLivroUseCase.execute(isbn);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<LivroResponse> findById(@PathVariable UUID id) {
        return findByIdLivroUseCase.execute(id);
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<LivroResponse> findByIsbn(@PathVariable String isbn) {
        return findByIsbnLivroUseCase.execute(isbn);
    }

    @GetMapping
    public ResponseEntity<List<LivroResponse>> findAll() {
        return findAllLivroUseCase.execute();
    }
}
