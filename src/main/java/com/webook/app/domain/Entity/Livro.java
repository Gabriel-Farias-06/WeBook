package com.webook.app.domain.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.webook.app.domain.Enums.ClassificacaoIndicativa;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "livro_id")
@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID livro_id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String sinopse;

    @Column(nullable = false)
    private  int numeroPaginas;

    @Column(nullable = false)
    private Double preco;

    @Column(nullable = false)
    private String caminhoLivro;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ClassificacaoIndicativa classificacaoIndicativa;

    @OneToOne(cascade = CascadeType.ALL)
    private Autor autor;

    @OneToOne(cascade = CascadeType.ALL)
    private Editora editora;

    @ManyToMany(mappedBy = "livros", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    private List<Usuario> usuarios;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "genero_livro", joinColumns = @JoinColumn(name = "livro_id"), inverseJoinColumns = @JoinColumn(name = "genero_id"))
    private List<Genero> generos;

    public Livro(UUID livro_id, String isbn, String titulo, String sinopse, int numeroPaginas, Double preco, String caminhoLivro, ClassificacaoIndicativa classificacaoIndicativa, Autor autor, Editora editora, List<Usuario> usuarios, List<Genero> generos) {
        this.livro_id = livro_id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.sinopse = sinopse;
        this.numeroPaginas = numeroPaginas;
        this.preco = preco;
        this.caminhoLivro = caminhoLivro;
        this.classificacaoIndicativa = classificacaoIndicativa;
        this.autor = autor;
        this.editora = editora;
        this.usuarios = usuarios;
        this.generos = generos;
    }

    public Livro() {
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
