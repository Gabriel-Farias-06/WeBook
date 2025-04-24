package com.webook.app.domain.Entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID genero_id;

    @Column(nullable = false, unique = true)
    private String nome;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "genero_livro", joinColumns = @JoinColumn(name = "genero_id"), inverseJoinColumns = @JoinColumn(name = "livro_id"))
    private List<Livro> livros;

    public Genero(UUID genero_id, String nome, List<Livro> livros) {
        this.genero_id = genero_id;
        this.nome = nome;
        this.livros = livros;
    }

    public Genero() {

    }

    public UUID getGenero_id() {
        return genero_id;
    }

    public void setGenero_id(UUID genero_id) {
        this.genero_id = genero_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

}
