package com.webook.app.application.UseCase.Usuario;

import com.webook.app.application.DTOs.Response.UsuarioResponse;
import com.webook.app.application.DTOs.UsuarioDTO;
import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public FindByIdUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<?> execute(UUID id) {
        var usuarioEncontrado = usuarioRepository.findById(id);
        if(usuarioEncontrado.isEmpty())
            return ResponseEntity.status(404).body("ID não encontrado no servidor");
        return ResponseEntity.ok(UsuarioDTO.toDTO(usuarioEncontrado.get()));
    }
}