package com.webook.app.application.UseCase.Livro;

import com.webook.app.application.DTOs.Request.LivroRequest;
import com.webook.app.application.DTOs.Response.LivroResponse;
import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Entity.Editora;
import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.LivroRepository;
import com.webook.app.domain.Interfaces.AutorRepository;
import com.webook.app.domain.Interfaces.EditoraRepository;
import com.webook.app.domain.Interfaces.GeneroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CreateLivroUseCase {
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final EditoraRepository editoraRepository;
    private final GeneroRepository generoRepository;
    public CreateLivroUseCase(LivroRepository livroRepository, AutorRepository autorRepository, EditoraRepository editoraRepository, GeneroRepository generoRepository){
        this.livroRepository = livroRepository;
        this.editoraRepository = editoraRepository;
        this.autorRepository = autorRepository;
        this.generoRepository = generoRepository;
    }

    @Transactional
    public ResponseEntity<LivroResponse> execute(LivroRequest livroRequest){
        Optional<Autor> autor = autorRepository.findById(livroRequest.getAutor_id());
        Optional<Editora> editora = editoraRepository.findById(livroRequest.getEditora_id());
        List<Genero> generos = livroRequest.getGeneros_id().stream().map(generoRepository::findById).filter(Optional::isPresent).map(Optional::get).toList();

        if(autor.isEmpty() || editora.isEmpty())
            return ResponseEntity.status(404).body(null);

        Livro livro = new Livro(livroRequest.getIsbn(), livroRequest.getTitulo(), livroRequest.getSinopse(),livroRequest.getNumeroPaginas(), livroRequest.getPreco(), livroRequest.getCaminhoLivro(), livroRequest.getClassificacaoIndicativa(), autor.get(), editora.get(), generos, livroRequest.getCaminhoEbook());

        if(livroRepository.findByIsbn(livro.getIsbn()).isPresent())
            return ResponseEntity.status(400).body(null);

        LivroResponse livroResponse = new LivroResponse(livroRepository.create(livro));

        return ResponseEntity.status(201).body(livroResponse);
    }
}
