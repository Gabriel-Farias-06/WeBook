package com.webook.app.application.DTOs;

import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Entity.Editora;
import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Enums.ClassificacaoIndicativa;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

public class LivroDTO {
    private UUID livro_id;

    private String isbn;

    private String titulo;

    private String sinopse;

    private  int numeroPaginas;

    private Double preco;

    private ClassificacaoIndicativa classificacaoIndicativa;

    private Autor autor;

    private Editora editora;

    private List<Usuario> usuarios;

    private List<Genero> generos;

    public LivroDTO(UUID livro_id, String isbn, String titulo, String sinopse, int numeroPaginas, Double preco, ClassificacaoIndicativa classificacaoIndicativa, Autor autor, Editora editora, List<Usuario> usuarios, List<Genero> generos) {
        this.livro_id = livro_id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.numeroPaginas = numeroPaginas;
        this.preco = preco;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.autor = autor;
        this.editora = editora;
        this.usuarios = usuarios;
        this.generos = generos;
    }

    public LivroDTO() {
    }

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

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }
}
