package com.webook.app.application.UseCase.Livro;

import com.webook.app.application.DTOs.LivroDTO;
import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.AutorRepository;
import com.webook.app.domain.Interfaces.EditoraRepository;
import com.webook.app.domain.Interfaces.GeneroRepository;
import com.webook.app.domain.Interfaces.LivroRepository;
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
        this.autorRepository = autorRepository;
        this.editoraRepository = editoraRepository;
        this.generoRepository = generoRepository;
    }

    @Transactional
    public Livro execute(LivroDTO livroDTO){
        if(livroRepository.findByIsbn(livroDTO.getIsbn()).isPresent())
            throw new IllegalArgumentException("Livro com mesmo ISBN já cadastrado");
        Livro livro = new Livro();
        livro.setIsbn(livroDTO.getIsbn());
        livro.setTitulo(livroDTO.getTitulo());
        livro.setSinopse(livroDTO.getSinopse());
        livro.setCaminhoLivro(livroDTO.getCaminhoLivro());
        livro.setNumeroPaginas(livroDTO.getNumeroPaginas());
        livro.setPreco(livroDTO.getPreco());
        livro.setClassificacaoIndicativa(livroDTO.getClassificacaoIndicativa());
        livro.setAutor(autorRepository.findById(livroDTO.getAutor()).orElseThrow());
        livro.setEditora(editoraRepository.findById(livroDTO.getEditora()).orElseThrow());
        livro.setGeneros(livroDTO.getGeneros().stream().map(generoRepository::findById).filter(Optional::isPresent).map(Optional::get).toList());
        livroRepository.create(livro);
        return livro;
    }
}
