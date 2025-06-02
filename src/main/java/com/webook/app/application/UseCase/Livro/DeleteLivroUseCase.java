package com.webook.app.application.UseCase.Livro;

import com.webook.app.application.DTOs.Response.LivroResponse;
import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Interfaces.LivroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteLivroUseCase {
    private final LivroRepository livroRepository;

    public DeleteLivroUseCase(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    @Transactional
    public ResponseEntity<Boolean> execute(String isbn){
        Optional<Livro> livro = livroRepository.findByIsbn(isbn);
        if(livro.isEmpty())
            return ResponseEntity.status(404).body(false);

        for (Genero genero : livro.get().getGeneros()) {
            genero.getLivros().remove(livro.get());
        }

        for (Usuario usuario : livro.get().getUsuarios()) {
            usuario.getLivros().remove(livro.get());
        }

        livro.get().setGeneros(new ArrayList<>());
        livro.get().setUsuarios(new ArrayList<>());

        livroRepository.delete(livro.get().getLivro_id());

        return  ResponseEntity.ok(true);
    }
}