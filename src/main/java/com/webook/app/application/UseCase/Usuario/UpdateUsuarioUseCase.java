package com.webook.app.application.UseCase.Usuario;

import com.webook.app.application.DTOs.Request.UsuarioUpdateRequest;
import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateUsuarioUseCase {
    private final UsuarioRepository usuarioRepository;

    public UpdateUsuarioUseCase(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public void execute(UsuarioUpdateRequest usuarioUpdateRequest) throws IllegalArgumentException {
        Optional<Usuario> usuario = usuarioRepository.findByEmail(usuarioUpdateRequest.getEmail());
        if(usuario.isEmpty())
            throw new IllegalArgumentException("Email já cadastrado");
        if(usuarioUpdateRequest.getNome() != null)
            usuario.get().setNome(usuarioUpdateRequest.getNome());
        if(usuarioUpdateRequest.getSenha() != null)
            usuario.get().setSenha(usuarioUpdateRequest.getSenha());
        if(usuarioUpdateRequest.getCaminhoFoto() != null)
            usuario.get().setCaminhoFoto(usuarioUpdateRequest.getCaminhoFoto());
        usuarioRepository.update(usuario.get());
    }
}