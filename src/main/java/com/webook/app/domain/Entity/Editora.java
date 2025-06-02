package com.webook.app.domain.Entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class Editora {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID editora_id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "editora", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Livro> livros = new ArrayList<>();

    public Editora(UUID editora_id, String nome) {
        this.editora_id = editora_id;
        this.nome = nome;
    }

    public Editora() {

    }

    public UUID getEditora_id() {
        return editora_id;
    }

    public void setEditora_id(UUID editora_id) {
        this.editora_id = editora_id;
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
