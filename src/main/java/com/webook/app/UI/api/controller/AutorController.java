package com.webook.app.UI.api.controller;

import com.webook.app.application.DTOs.AutorDTO;
import com.webook.app.application.DTOs.Response.AutorResponse;
import com.webook.app.application.UseCase.Autor.*;
import com.webook.app.domain.Entity.Autor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/autor")
public class AutorController {
    private final CreateAutorUseCase createAutorUseCase;
    private final UpdateAutorUseCase updateAutorUseCase;
    private final DeleteAutorUseCase deleteAutorUseCase;
    private final FindByIdAutorUseCase findByIdAutorUseCase;

    public AutorController(CreateAutorUseCase createAutorUseCase, UpdateAutorUseCase updateAutorUseCase, DeleteAutorUseCase deleteAutorUseCase, FindByIdAutorUseCase findByIdAutorUseCase) {
        this.createAutorUseCase = createAutorUseCase;
        this.updateAutorUseCase = updateAutorUseCase;
        this.deleteAutorUseCase = deleteAutorUseCase;
        this.findByIdAutorUseCase = findByIdAutorUseCase;
    }

    @PostMapping
    public ResponseEntity<AutorDTO> create(@RequestBody Autor autor) {
        return createAutorUseCase.execute(autor);
    }

    @PutMapping
    public ResponseEntity<AutorResponse> update(@RequestBody Autor autor) {
        return updateAutorUseCase.execute(autor);
    }

    @DeleteMapping("/{nome}/{sobrenome}")
    public ResponseEntity<Boolean> delete(@PathVariable String nome, @PathVariable String sobrenome) {
        return deleteAutorUseCase.execute(nome, sobrenome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> findById(@PathVariable UUID id) {
        Optional<Autor> autor = findByIdAutorUseCase.execute(id);
        return autor.map(value -> ResponseEntity.ok(AutorDTO.toDTO(value))).orElseGet(() -> ResponseEntity.status(404).body(null));
    }
}
