package com.webook.app.application.UseCase.Usuario;

import com.webook.app.application.DTOs.Request.UsuarioUpdateRequest;
import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UpdateUsuarioUseCase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> execute(UsuarioUpdateRequest usuarioUpdateRequest) throws IllegalArgumentException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioUpdateRequest.getEmail());
        if(usuario.isEmpty())
            return ResponseEntity.status(404).body("Usuário não encontrado");
        if(!passwordEncoder.matches(usuarioUpdateRequest.getSenhaAtual(), usuario.get().getSenha()))
            return ResponseEntity.status(401).body("Senha incorreta");
        if(usuarioUpdateRequest.getNome() != null)
            usuario.get().setNome(passwordEncoder.encode(usuarioUpdateRequest.getNome()));
        if(usuarioUpdateRequest.getSenhaNova() != null)
            usuario.get().setSenha(usuarioUpdateRequest.getSenhaNova());
        if(usuarioUpdateRequest.getCaminhoFoto() != null)
            usuario.get().setCaminhoFoto(usuarioUpdateRequest.getCaminhoFoto());

        return ResponseEntity.ok(usuarioRepository.update(usuario.get()));
    }
}