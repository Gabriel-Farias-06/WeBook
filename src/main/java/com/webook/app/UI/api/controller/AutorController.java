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

    public AutorController(CreateAutorUseCase createAutorUseCase, UpdateAutorUseCase updateAutorUseCase, DeleteAutorUseCase deleteAutorUseCase, FindByIdAutorUseCase findByIdAutorUseCase) {
        this.createAutorUseCase = createAutorUseCase;
        this.updateAutorUseCase = updateAutorUseCase;
        this.deleteAutorUseCase = deleteAutorUseCase;
        this.findByIdAutorUseCase = findByIdAutorUseCase;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable UUID id) {
        if(findByIdAutorUseCase.execute(id).isPresent()) {
            deleteAutorUseCase.execute(findByIdAutorUseCase.execute(id).get().getAutor_id());
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<AutorDTO> findById(@PathVariable UUID id) {
        if(findByIdAutorUseCase.execute(id).isPresent())
            return ResponseEntity.ok(AutorDTO.toDTO(findByIdAutorUseCase.execute(id).get()));
        return ResponseEntity.notFound().build();
    }
}
