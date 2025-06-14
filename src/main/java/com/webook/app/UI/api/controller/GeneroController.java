package com.webook.app.UI.api.controller;

import com.webook.app.application.DTOs.GeneroDTO;
import com.webook.app.application.DTOs.Request.GeneroRequest;
import com.webook.app.application.DTOs.UsuarioDTO;
import com.webook.app.application.UseCase.Genero.*;
import com.webook.app.domain.Entity.Genero;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/genero")
public class GeneroController {
    private final CreateGeneroUseCase createGeneroUseCase;
    private final UpdateGeneroUseCase updateGeneroUseCase;
    private final DeleteGeneroUseCase deleteGeneroUseCase;
    private final FindByIdGeneroUseCase findByIdGeneroUseCase;
    private final FindByNomeGeneroUseCase findByNomeGeneroUseCase;

    public GeneroController(CreateGeneroUseCase createGeneroUseCase, UpdateGeneroUseCase updateGeneroUseCase, DeleteGeneroUseCase deleteGeneroUseCase, FindByIdGeneroUseCase findByIdGeneroUseCase, FindByNomeGeneroUseCase findByNomeGeneroUseCase) {
        this.createGeneroUseCase = createGeneroUseCase;
        this.updateGeneroUseCase = updateGeneroUseCase;
        this.deleteGeneroUseCase = deleteGeneroUseCase;
        this.findByIdGeneroUseCase = findByIdGeneroUseCase;
        this.findByNomeGeneroUseCase = findByNomeGeneroUseCase;
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> create(@RequestBody GeneroRequest generoRequest) {
        Genero genero = new Genero(generoRequest.getNome());
        Genero generoCriado = createGeneroUseCase.execute(genero);
        URI localizacao = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(generoCriado.getGenero_id()).toUri();
        return ResponseEntity.created(localizacao).body(GeneroDTO.toDTO(generoCriado));
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody GeneroRequest generoRequest) {
        Genero genero = new Genero(generoRequest.getNome());
        updateGeneroUseCase.execute(genero);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> delete(@PathVariable String name) {
        deleteGeneroUseCase.execute(findByNomeGeneroUseCase.execute(name).get().getGenero_id());
        return ResponseEntity.ok(true);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Optional<Genero>> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(findByIdGeneroUseCase.execute(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Optional<Genero>> findById(@PathVariable String name) {
        return ResponseEntity.ok(findByNomeGeneroUseCase.execute(name));
    }
}
