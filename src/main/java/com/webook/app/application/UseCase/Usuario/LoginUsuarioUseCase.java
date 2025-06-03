package com.webook.app.application.UseCase.Usuario;

import com.webook.app.application.DTOs.Request.UsuarioRequest;
import com.webook.app.application.DTOs.Response.UsuarioResponse;
import com.webook.app.application.DTOs.UsuarioDTO;
import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginUsuarioUseCase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> execute(UsuarioRequest usuarioRequest, HttpSession session) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(usuarioRequest.getEmail());

        if (usuarioEncontrado.isEmpty())
            return ResponseEntity.status(404).body("Usuário não encontrado");

        if (!passwordEncoder.matches(usuarioRequest.getSenha(), usuarioEncontrado.get().getSenha()))
            return ResponseEntity.status(401).body("Senha incorreta");

        session.setAttribute("usuario", UsuarioResponse.toDTO(usuarioEncontrado.get()));

        return ResponseEntity.ok(UsuarioResponse.toDTO(usuarioEncontrado.get()));
    } 
}