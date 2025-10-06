package com.webook.app.application.UseCase.Usuario;

import com.webook.app.application.UseCase.JwtService;
import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Exceptions.EmailAlreadyExistsException;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
public class CreateUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public CreateUsuarioUseCase(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Transactional
    public ResponseEntity<?> execute(Usuario usuario) throws EmailAlreadyExistsException {
        if(usuarioRepository.findByEmail(usuario.getEmail()).isPresent())
            return ResponseEntity.status(401).body("Email já presente no servidor");
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Usuario usuarioCreated = usuarioRepository.create(usuario);

        String token = jwtService.generateToken(
                usuarioCreated.getEmail(),
                Map.of("usuario_id", usuarioCreated.getUsuario_id().toString()));

        return ResponseEntity.ok(Map.of(
                "token", token
        ));
    }
}
