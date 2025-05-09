package com.webook.app.UI.api.controller;

import com.webook.app.application.DTOs.LivroDTO;
import com.webook.app.application.UseCase.Livro.*;
import com.webook.app.domain.Entity.Livro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    public LivroController(CreateLivroUseCase createLivroUseCase, UpdateLivroUseCase updateLivroUseCase, DeleteLivroUseCase deleteLivroUseCase, FindByIdLivroUseCase findByIdLivroUseCase, FindByIsbnLivroUseCase findByIsbnLivroUseCase) {
        this.createLivroUseCase = createLivroUseCase;
        this.updateLivroUseCase = updateLivroUseCase;
        this.deleteLivroUseCase = deleteLivroUseCase;
        this.findByIdLivroUseCase = findByIdLivroUseCase;
        this.findByIsbnLivroUseCase = findByIsbnLivroUseCase;
    }

    @PostMapping
    public ResponseEntity<LivroDTO> create(@RequestBody Livro livro) {
        Livro livroCriado = createLivroUseCase.execute(livro);
        URI localizacao = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(livroCriado.getLivro_id()).toUri();
        return ResponseEntity.created(localizacao).body(LivroDTO.toDTO(livroCriado));
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody Livro livro) {
        updateLivroUseCase.execute(livro);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<Boolean> delete(@PathVariable String isbn) {
        deleteLivroUseCase.execute(findByIsbnLivroUseCase.execute(isbn).get().getLivro_id());
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Livro>> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(findByIdLivroUseCase.execute(id));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Optional<Livro>> findByIsbn(@PathVariable String isbn) {
        return ResponseEntity.ok(findByIsbnLivroUseCase.execute(isbn));
    }
}
