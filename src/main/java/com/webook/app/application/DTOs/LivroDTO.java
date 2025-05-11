package com.webook.app.application.DTOs;

import com.webook.app.domain.Entity.*;
import com.webook.app.domain.Enums.ClassificacaoIndicativa;

import java.util.List;
import java.util.UUID;

public class LivroDTO {
    private UUID livro_id;

    private String isbn;

    private String titulo;

    private String sinopse;

    private String caminhoLivro;

    private int numeroPaginas;

    private Double preco;

    private ClassificacaoIndicativa classificacaoIndicativa;

    private UUID autor;

    private UUID editora;

    private List<UUID> generos;

    public static LivroDTO toDTO(Livro livro){
        if(livro.getLivro_id() == null)
            return new LivroDTO(livro.getIsbn(), livro.getTitulo(), livro.getSinopse(), livro.getCaminhoLivro(), livro.getNumeroPaginas(), livro.getPreco(), livro.getClassificacaoIndicativa(), livro.getAutor().getAutor_id(), livro.getEditora().getEditora_id(), livro.getGeneros().stream().map(Genero::getGenero_id).toList());

        return new LivroDTO(livro.getLivro_id(), livro.getIsbn(), livro.getTitulo(), livro.getSinopse(), livro.getCaminhoLivro(), livro.getNumeroPaginas(), livro.getPreco(), livro.getClassificacaoIndicativa(), livro.getAutor().getAutor_id(), livro.getEditora().getEditora_id(), livro.getGeneros().stream().map(Genero::getGenero_id).toList());
    }

    public LivroDTO(UUID livro_id, String isbn, String titulo, String sinopse, String caminhoLivro, int numeroPaginas, Double preco, ClassificacaoIndicativa classificacaoIndicativa, UUID autor, UUID editora, List<UUID> generos) {
        this.livro_id = livro_id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.caminhoLivro = caminhoLivro;
        this.numeroPaginas = numeroPaginas;
        this.preco = preco;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.autor = autor;
        this.editora = editora;
        this.generos = generos;
    }

    public LivroDTO(String isbn, String titulo, String sinopse, String caminhoLivro, int numeroPaginas, Double preco, ClassificacaoIndicativa classificacaoIndicativa, UUID autor, UUID editora, List<UUID> generos) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.caminhoLivro = caminhoLivro;
        this.numeroPaginas = numeroPaginas;
        this.preco = preco;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.autor = autor;
        this.editora = editora;
        this.generos = generos;
    }

    public LivroDTO() {}

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

    public ClassificacaoIndicativa getClassificacaoIndicativa() {
        return classificacaoIndicativa;
    }

    public void setClassificacaoIndicativa(ClassificacaoIndicativa classificacaoIndicativa) {
        this.classificacaoIndicativa = classificacaoIndicativa;
    }

    public UUID getAutor() {
        return autor;
    }

    public void setAutor(UUID autor) {
        this.autor = autor;
    }

    public UUID getEditora() {
        return editora;
    }

    public void setEditora(UUID editora) {
        this.editora = editora;
    }

    public List<UUID> getGeneros() {
        return generos;
    }

    public void setGeneros(List<UUID> generos) {
        this.generos = generos;
    }

    public String getCaminhoLivro() {
        return caminhoLivro;
    }

    public void setCaminhoLivro(String caminhoLivro) {
        this.caminhoLivro = caminhoLivro;
    }
}
