package com.webook.app.application.DTOs.Request;

import com.webook.app.domain.Enums.ClassificacaoIndicativa;

import java.util.List;
import java.util.UUID;

public class LivroRequest {
    String isbn;
    String titulo;
    String sinopse;
    int numeroPaginas;
    Double preco;
    String caminhoLivro;
    ClassificacaoIndicativa classificacaoIndicativa;
    String caminhoEbook;
    UUID autor_id;
    UUID editora_id;
    List<UUID> generos_id;

    public LivroRequest(String isbn, String titulo, String sinopse, int numeroPaginas, Double preco, String caminhoLivro, ClassificacaoIndicativa classificacaoIndicativa, String caminhoEbook, UUID autor_id, UUID editora_id, List<UUID> generos_id) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.numeroPaginas = numeroPaginas;
        this.preco = preco;
        this.caminhoLivro = caminhoLivro;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.caminhoEbook = caminhoEbook;
        this.autor_id = autor_id;
        this.editora_id = editora_id;
        this.generos_id = generos_id;
    }

    public LivroRequest() {}

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

    public UUID getAutor_id() {
        return autor_id;
    }

    public void setAutor_id(UUID autor_id) {
        this.autor_id = autor_id;
    }

    public UUID getEditora_id() {
        return editora_id;
    }

    public void setEditora_id(UUID editora_id) {
        this.editora_id = editora_id;
    }

    public List<UUID> getGeneros_id() {
        return generos_id;
    }

    public void setGeneros_id(List<UUID> generos_id) {
        this.generos_id = generos_id;
    }

    public String getCaminhoEbook() {
        return caminhoEbook;
    }

    public void setCaminhoEbook(String caminhoEbook) {
        this.caminhoEbook = caminhoEbook;
    }
}
