package com.webook.app.application.UseCase.Usuario;

import com.webook.app.application.DTOs.UsuarioDTO;
import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FindByEmailUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public FindByEmailUsuarioUseCase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<UsuarioDTO> execute(String email, String senha) throws IllegalArgumentException {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findByEmail(email);
        if(usuarioEncontrado.isEmpty())
            return ResponseEntity.status(404).body(null);
        else if(passwordEncoder.matches(senha, usuarioEncontrado.get().getSenha()))
            return ResponseEntity.status(401).body(null);

        return ResponseEntity.ok(UsuarioDTO.toDTO(usuarioEncontrado.get()));
    }
}