package com.webook.app.application.DTOs.Response;

import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Entity.Editora;
import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Enums.ClassificacaoIndicativa;

import java.util.List;

public class LivroResponse {
    String isbn;
    String titulo;
    String sinopse;
    int numeroPaginas;
    Double preco;
    String caminhoLivro;
    ClassificacaoIndicativa classificacaoIndicativa;
    Autor autor;
    Editora editora;
    List<Genero> generos;

    public LivroResponse(String isbn, String titulo, String sinopse, int numeroPaginas, Double preco, String caminhoLivro, ClassificacaoIndicativa classificacaoIndicativa, Autor autor, Editora editora, List<Genero> generos) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.numeroPaginas = numeroPaginas;
        this.preco = preco;
        this.caminhoLivro = caminhoLivro;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.autor = autor;
        this.editora = editora;
        this.generos = generos;
    }

    public LivroResponse() {}

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

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }
}
