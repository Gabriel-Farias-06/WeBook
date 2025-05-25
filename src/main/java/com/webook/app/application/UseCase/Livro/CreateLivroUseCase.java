package com.webook.app.application.UseCase.Livro;

import com.webook.app.application.DTOs.LivroDTO;
import com.webook.app.application.DTOs.Response.LivroResponse;
import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.AutorRepository;
import com.webook.app.domain.Interfaces.EditoraRepository;
import com.webook.app.domain.Interfaces.GeneroRepository;
import com.webook.app.domain.Interfaces.LivroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CreateLivroUseCase {
    private final LivroRepository livroRepository;
    public CreateLivroUseCase(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    @Transactional
    public ResponseEntity<LivroResponse> execute(Livro livro){
        if(livroRepository.findByIsbn(livro.getIsbn()).isPresent())
            return ResponseEntity.status(400).body(null);
        livroRepository.create(livro);

        return ResponseEntity.ok(new LivroResponse(livro.getIsbn(), livro.getTitulo(), livro.getSinopse(), livro.getNumeroPaginas(), livro.getPreco(), livro.getCaminhoLivro(), livro.getClassificacaoIndicativa(), livro.getAutor(), livro.getEditora(), livro.getGeneros()));
    }
}
