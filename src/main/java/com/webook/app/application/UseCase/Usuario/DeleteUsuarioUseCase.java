package com.webook.app.application.UseCase.Usuario;

import com.webook.app.application.DTOs.Response.UsuarioResponse;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class DeleteUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public DeleteUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ResponseEntity<Boolean> execute(String email, String senha) throws IllegalArgumentException {
        var usuarioEncontrado = usuarioRepository.findByEmail(email);
        if(usuarioEncontrado.isEmpty())
            return ResponseEntity.status(404).body(false);
        else if(!usuarioEncontrado.get().getSenha().equals(senha))
            return ResponseEntity.status(401).body(false);

        usuarioEncontrado.get().setLivros(new ArrayList<>());

        usuarioRepository.update(usuarioEncontrado.get());

        usuarioRepository.delete(usuarioEncontrado.get().getUsuario_id());

        return ResponseEntity.ok(true);
    }
}