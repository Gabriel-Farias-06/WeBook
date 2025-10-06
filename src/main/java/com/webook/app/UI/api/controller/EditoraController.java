package com.webook.app.UI.api.controller;

import com.webook.app.application.DTOs.EditoraDTO;
import com.webook.app.application.UseCase.Editora.*;
import com.webook.app.domain.Entity.Editora;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/editora")
public class EditoraController {
    private final CreateEditoraUseCase createEditoraUseCase;
    private final UpdateEditoraUseCase updateEditoraUseCase;
    private final DeleteEditoraUseCase deleteEditoraUseCase;
    private final FindByIdEditoraUseCase findByIdEditoraUseCase;
    private final FindByNomeEditoraUseCase findByNomeEditoraUseCase;

    public EditoraController(CreateEditoraUseCase createEditoraUseCase, UpdateEditoraUseCase updateEditoraUseCase, DeleteEditoraUseCase deleteEditoraUseCase, FindByIdEditoraUseCase findByIdEditoraUseCase, FindByNomeEditoraUseCase findByNomeEditoraUseCase) {
        this.createEditoraUseCase = createEditoraUseCase;
        this.updateEditoraUseCase = updateEditoraUseCase;
        this.deleteEditoraUseCase = deleteEditoraUseCase;
        this.findByIdEditoraUseCase = findByIdEditoraUseCase;
        this.findByNomeEditoraUseCase = findByNomeEditoraUseCase;
    }

    @PostMapping
    public ResponseEntity<EditoraDTO> create(@RequestBody Editora editora) {
        return createEditoraUseCase.execute(editora);
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody Editora editora) {
        updateEditoraUseCase.execute(editora);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Boolean> delete(@PathVariable String name) {
        deleteEditoraUseCase.execute(findByNomeEditoraUseCase.execute(name).get().getEditora_id());
        return ResponseEntity.ok(true);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<EditoraDTO> findById(@PathVariable UUID id) {
        Optional<Editora> editora = findByIdEditoraUseCase.execute(id);
        return editora.map(value -> ResponseEntity.ok(EditoraDTO.toDTO(value))).orElseGet(() -> ResponseEntity.status(404).body(null));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<EditoraDTO> findById(@PathVariable String name) {
        Optional<Editora> editora = findByNomeEditoraUseCase.execute(name);
        return editora.map(value -> ResponseEntity.ok(EditoraDTO.toDTO(value))).orElseGet(() -> ResponseEntity.status(404).body(null));
    }
}
