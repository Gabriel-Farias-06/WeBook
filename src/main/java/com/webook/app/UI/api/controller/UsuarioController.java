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
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioRequest usuarioRequest) throws EmailAlreadyExistsException, EmailInvalidoException, SenhaInvalidaException {
        Usuario usuario = new Usuario(usuarioRequest.getEmail(), usuarioRequest.getSenha());
        Usuario usuarioCriado = createUsuarioUseCase.execute(usuario);
        URI localizacao = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioCriado.getUsuario_id()).toUri();
        return ResponseEntity.created(localizacao).body(UsuarioDTO.toDTO(usuarioCriado));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioRequest usuarioRequest, HttpSession session) {
        return loginUsuarioUseCase.execute(usuarioRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(HttpSession session) {
        if(session.getAttribute("usuario") == null)
            return ResponseEntity.status(404).body(false);

        session.invalidate();
        return ResponseEntity.ok(true);
    }

    @PutMapping
    public ResponseEntity<Boolean> update(@RequestBody UsuarioUpdateRequest usuarioUpdateRequest) throws EmailInvalidoException, SenhaInvalidaException {
        updateUsuarioUseCase.execute(usuarioUpdateRequest);
        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/{email}/{senha}")
    public ResponseEntity<Boolean> delete(@PathVariable String email, @PathVariable String senha) {
        deleteUsuarioUseCase.execute(email, senha);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable UUID id) throws EmailInvalidoException, SenhaInvalidaException {
        return ResponseEntity.ok(UsuarioDTO.toDTO(findByIdUsuarioUseCase.execute(id).get()));
    }

    @PostMapping("/{usuario_id}/livros/{livro_id}")
    public ResponseEntity<String> addRelationshipUsuarioLivro(@PathVariable UUID usuario_id, @PathVariable UUID livro_id) {
        if(addRelationshipUsuarioLivroUseCase.execute(usuario_id, livro_id))
            return ResponseEntity.ok("Relação criada com sucesso");
        else
            return ResponseEntity.notFound().build();
    }
}
