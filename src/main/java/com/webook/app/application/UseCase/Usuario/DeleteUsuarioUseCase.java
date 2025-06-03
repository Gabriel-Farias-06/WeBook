package com.webook.app.application.UseCase.Usuario;

import com.webook.app.application.DTOs.Response.UsuarioResponse;
import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class DeleteUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public DeleteUsuarioUseCase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResponseEntity<Boolean> execute(String email, String senha) throws IllegalArgumentException {
        var usuarioEncontrado = usuarioRepository.findByEmail(email);
        if(usuarioEncontrado.isEmpty())
            return ResponseEntity.status(404).body(false);
        else if(!passwordEncoder.matches(senha, usuarioEncontrado.get().getSenha()))
            return ResponseEntity.status(401).body(false);

        for (Livro livro: usuarioEncontrado.get().getLivros()) {
            livro.getUsuarios().remove(usuarioEncontrado.get());
        }

        usuarioEncontrado.get().setLivros(new ArrayList<>());

        usuarioRepository.delete(usuarioEncontrado.get().getUsuario_id());

        return ResponseEntity.ok(true);
    }
}