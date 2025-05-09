package com.webook.app.UI.api.controller;

import com.webook.app.application.DTOs.AutorDTO;
import com.webook.app.application.UseCase.Autor.*;
import com.webook.app.domain.Entity.Autor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/autor")
public class AutorController {
    private final CreateAutorUseCase createAutorUseCase;
    private final UpdateAutorUseCase updateAutorUseCase;
    private final DeleteAutorUseCase deleteAutorUseCase;
    private final FindByIdAutorUseCase findByIdAutorUseCase;
    private final FindByNomeAutorUseCase findByNomeAutorUseCase;

    public AutorController(CreateAutorUseCase createAutorUseCase, UpdateAutorUseCase updateAutorUseCase, DeleteAutorUseCase deleteAutorUseCase, FindByIdAutorUseCase findByIdAutorUseCase, FindByNomeAutorUseCase findByNomeAutorUseCase) {
        this.createAutorUseCase = createAutorUseCase;
        this.updateAutorUseCase = updateAutorUseCase;
        this.deleteAutorUseCase = deleteAutorUseCase;
        this.findByIdAutorUseCase = findByIdAutorUseCase;
        this.findByNomeAutorUseCase = findByNomeAutorUseCase;
    }

    @PostMapping
    public ResponseEntity<AutorDTO> create(@RequestBody Autor autor) {
        Autor autorCriado = createAutorUseCase.execute(autor);
        URI localizacao = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorCriado.getAutor_id()).toUri();
        return ResponseEntity.created(localizacao).body(AutorDTO.toDTO(autorCriado));
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody Autor autor) {
        updateAutorUseCase.execute(autor);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> delete(@PathVariable String name) {
        deleteAutorUseCase.execute(findByNomeAutorUseCase.execute(name).get().getAutor_id());
        return ResponseEntity.ok(true);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AutorDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(AutorDTO.toDTO(findByIdAutorUseCase.execute(id).get()));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<AutorDTO> findByName(@PathVariable String name) {
        return ResponseEntity.ok(AutorDTO.toDTO(findByNomeAutorUseCase.execute(name).get()));
    }
}
