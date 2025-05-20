package com.webook.app.UI.api.controller;

import com.webook.app.application.DTOs.UsuarioDTO;
import com.webook.app.application.UseCase.Usuario.*;
import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Exceptions.EmailAlreadyExistsException;
import com.webook.app.domain.Exceptions.EmailInvalidoException;
import com.webook.app.domain.Exceptions.SenhaInvalidaException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    private final CreateUsuarioUseCase createUsuarioUseCase;
    private final UpdateUsuarioUseCase updateUsuarioUseCase;
    private final DeleteUsuarioUseCase deleteUsuarioUseCase;
    private final FindByIdUsuarioUseCase findByIdUsuarioUseCase;
    private final FindByEmailUsuarioUseCase findByEmailUsuarioUseCase;
    private final AddRelationshipUsuarioLivroUseCase addRelationshipUsuarioLivroUseCase;

    public UsuarioController(CreateUsuarioUseCase createUsuarioUseCase, UpdateUsuarioUseCase updateUsuarioUseCase, DeleteUsuarioUseCase deleteUsuarioUseCase, FindByIdUsuarioUseCase findByIdUsuarioUseCase, FindByEmailUsuarioUseCase findByEmailUsuarioUseCase, AddRelationshipUsuarioLivroUseCase addRelationshipUsuarioLivroUseCase) {
        this.createUsuarioUseCase = createUsuarioUseCase;
        this.updateUsuarioUseCase = updateUsuarioUseCase;
        this.deleteUsuarioUseCase = deleteUsuarioUseCase;
        this.findByIdUsuarioUseCase = findByIdUsuarioUseCase;
        this.findByEmailUsuarioUseCase = findByEmailUsuarioUseCase;
        this.addRelationshipUsuarioLivroUseCase = addRelationshipUsuarioLivroUseCase;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody Usuario usuario) throws EmailAlreadyExistsException {
        Usuario usuarioCriado = createUsuarioUseCase.execute(usuario);
        URI localizacao = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioCriado.getUsuario_id()).toUri();
        return ResponseEntity.created(localizacao).body(UsuarioDTO.toDTO(usuarioCriado));
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody Usuario usuario) {
        updateUsuarioUseCase.execute(usuario);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{email}/{senha}")
    public ResponseEntity<Boolean> delete(@PathVariable String email, @PathVariable String senha) {
        deleteUsuarioUseCase.execute(findByEmailUsuarioUseCase.execute(email, senha).get().getUsuario_id());
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable UUID id) throws EmailInvalidoException, SenhaInvalidaException {
        return ResponseEntity.ok(UsuarioDTO.toDTO(findByIdUsuarioUseCase.execute(id).get()));
    }

    @GetMapping("/{email}/{senha}")
    public ResponseEntity<UsuarioDTO> findByIsbn(@PathVariable String email, @PathVariable String senha) {
        return ResponseEntity.ok(UsuarioDTO.toDTO(findByEmailUsuarioUseCase.execute(email, senha).get()));
    }

    @PostMapping("/{usuario_id}/livros/{livro_id}")
    public ResponseEntity<String> addRelationshipUsuarioLivro(@PathVariable UUID usuario_id, @PathVariable UUID livro_id) {
        if(addRelationshipUsuarioLivroUseCase.execute(usuario_id, livro_id))
            return ResponseEntity.ok("Relação criada com sucesso");
        else
            return ResponseEntity.notFound().build();
    }
}
