package com.webook.app.application.DTOs.Response;

import com.webook.app.application.DTOs.EditoraDTO;
import com.webook.app.application.DTOs.GeneroDTO;
import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Entity.Editora;
import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Enums.ClassificacaoIndicativa;

import java.util.List;
import java.util.UUID;

public class LivroResponse {
    UUID livro_id;
    String isbn;
    String titulo;
    String sinopse;
    int numeroPaginas;
    Double preco;
    String caminhoLivro;
    ClassificacaoIndicativa classificacaoIndicativa;
    AutorResponse autor;
    EditoraDTO editora;
    List<GeneroDTO> generos;

    public LivroResponse(Livro livro) {
        this.livro_id = livro.getLivro_id();
        this.isbn = livro.getIsbn();
        this.titulo = livro.getTitulo();
        this.sinopse = livro.getSinopse();
        this.numeroPaginas = livro.getNumeroPaginas();
        this.preco = livro.getPreco();
        this.caminhoLivro = livro.getCaminhoLivro();
        this.classificacaoIndicativa = livro.getClassificacaoIndicativa();
        this.autor = new AutorResponse(livro.getAutor().getNome(), livro.getAutor().getSobrenome());
        this.editora = EditoraDTO.toDTO(livro.getEditora());
        this.generos = livro.getGeneros().stream().map(GeneroDTO::toDTO).toList();
    }

    public LivroResponse() {}

    public UUID getLivro_id() {
        return livro_id;
    }

    public void setLivro_id(UUID livro_id) {
        this.livro_id = livro_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getCaminhoLivro() {
        return caminhoLivro;
    }

    public void setCaminhoLivro(String caminhoLivro) {
        this.caminhoLivro = caminhoLivro;
    }

    public ClassificacaoIndicativa getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(ClassificacaoIndicativa classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public AutorResponse getAutor() {
        return autor;
    }

    public void setAutor(AutorResponse autor) {
        this.autor = autor;
    }

    public EditoraDTO getEditora() {
        return editora;
    }

    public void setEditora(EditoraDTO editora) {
        this.editora = editora;
    }

    public List<GeneroDTO> getGeneros() {
        return generos;
    }

    public void setGeneros(List<GeneroDTO> generos) {
        this.generos = generos;
    }
}
