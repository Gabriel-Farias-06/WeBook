package com.webook.app.application.UseCase.Usuario;

import com.webook.app.application.DTOs.Request.UsuarioRequest;
import com.webook.app.application.DTOs.UsuarioDTO;
import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public LoginUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public ResponseEntity<Boolean> execute(UsuarioRequest usuarioRequest, HttpSession session) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(usuarioRequest.getEmail());
        if(usuarioEncontrado.isEmpty())
            return ResponseEntity.status(404).body(false);
        else if(!usuarioEncontrado.get().getSenha().equals(usuarioRequest.getSenha())) {
            System.out.println(usuarioEncontrado.get().getSenha());
            System.out.println(usuarioRequest.getSenha());
            return ResponseEntity.status(401).body(false);
        }

        session.setAttribute("usuario", UsuarioDTO.toDTO(usuarioEncontrado.get()));
        return ResponseEntity.ok(true);
    }
}