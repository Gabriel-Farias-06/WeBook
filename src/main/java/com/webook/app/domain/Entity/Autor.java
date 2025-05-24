package com.webook.app.domain.Entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private UUID autor_id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;


    public Autor(UUID autor_id, String nome, String sobrenome) {
        this.autor_id = autor_id;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public Autor() {

    }

    public UUID getAutor_id() {
        return autor_id;
    }

    public void setAutor_id(UUID autor_id) {
        this.autor_id = autor_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }
}
