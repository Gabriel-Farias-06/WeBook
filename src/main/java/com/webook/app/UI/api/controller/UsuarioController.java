package com.webook.app.UI.api.controller;

import com.webook.app.application.DTOs.Request.UsuarioRequest;
import com.webook.app.application.DTOs.Request.UsuarioUpdateRequest;
import com.webook.app.application.DTOs.UsuarioDTO;
import com.webook.app.application.UseCase.Usuario.*;
import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Exceptions.EmailAlreadyExistsException;
import com.webook.app.domain.Exceptions.EmailInvalidoException;
import com.webook.app.domain.Exceptions.SenhaInvalidaException;
import jakarta.servlet.http.HttpSession;
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
    private final AddRelationshipUsuarioLivroUseCase addRelationshipUsuarioLivroUseCase;
    private final LoginUsuarioUseCase loginUsuarioUseCase;

    public UsuarioController(CreateUsuarioUseCase createUsuarioUseCase, UpdateUsuarioUseCase updateUsuarioUseCase, DeleteUsuarioUseCase deleteUsuarioUseCase, FindByIdUsuarioUseCase findByIdUsuarioUseCase, AddRelationshipUsuarioLivroUseCase addRelationshipUsuarioLivroUseCase, LoginUsuarioUseCase loginUsuarioUseCase) {
        this.createUsuarioUseCase = createUsuarioUseCase;
        this.updateUsuarioUseCase = updateUsuarioUseCase;
        this.deleteUsuarioUseCase = deleteUsuarioUseCase;
        this.findByIdUsuarioUseCase = findByIdUsuarioUseCase;
        this.addRelationshipUsuarioLivroUseCase = addRelationshipUsuarioLivroUseCase;
        this.loginUsuarioUseCase = loginUsuarioUseCase;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> create(@RequestBody UsuarioRequest usuarioRequest) throws EmailAlreadyExistsException, EmailInvalidoException, SenhaInvalidaException {
        Usuario usuario = new Usuario(usuarioRequest.getEmail(), usuarioRequest.getSenha());
        return createUsuarioUseCase.execute(usuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioRequest usuarioRequest, HttpSession session) {
        return loginUsuarioUseCase.execute(usuarioRequest);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody UsuarioUpdateRequest usuarioUpdateRequest) {
        return updateUsuarioUseCase.execute(usuarioUpdateRequest);
    }

    @DeleteMapping("/{email}/{senha}")
    public ResponseEntity<Boolean> delete(@PathVariable String email, @PathVariable String senha) {
        return deleteUsuarioUseCase.execute(email, senha);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        return findByIdUsuarioUseCase.execute(id);
    }

    @PostMapping("/{usuario_id}/livros/{livro_id}")
    public ResponseEntity<String> addRelationshipUsuarioLivro(@PathVariable UUID usuario_id, @PathVariable UUID livro_id) {
        if(addRelationshipUsuarioLivroUseCase.execute(usuario_id, livro_id))
            return ResponseEntity.ok("Relação criada com sucesso");
        else
            return ResponseEntity.notFound().build();
    }
}
