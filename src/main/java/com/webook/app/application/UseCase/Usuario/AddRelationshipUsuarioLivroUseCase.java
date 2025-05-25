package com.webook.app.application.UseCase.Usuario;

import com.webook.app.domain.Interfaces.LivroRepository;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Service
public class AddRelationshipUsuarioLivroUseCase {
    private final UsuarioRepository usuarioRepository;
    private final LivroRepository livroRepository;

    public AddRelationshipUsuarioLivroUseCase(UsuarioRepository usuarioRepository, LivroRepository livroRepository){
        this.usuarioRepository = usuarioRepository;
        this.livroRepository = livroRepository;
    }

    public boolean execute(UUID usuarioId, UUID livroId){
        if(livroRepository.findById(livroId).isEmpty() || usuarioRepository.findById(usuarioId).isEmpty())
            return false;
        livroRepository.findById(livroId).get().getUsuarios().add(usuarioRepository.findById(usuarioId).get());
        usuarioRepository.findById(usuarioId).get().getLivros().add(livroRepository.findById(livroId).get());
        usuarioRepository.update(usuarioRepository.findById(usuarioId).get());
        return true;
    }
}
