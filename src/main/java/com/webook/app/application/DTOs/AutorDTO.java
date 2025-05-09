package com.webook.app.application.DTOs;

import com.webook.app.domain.Entity.Autor;

import java.util.UUID;

public class AutorDTO {
    private UUID autor_id;

    private String nome;

    private String sobrenome;

    public static AutorDTO toDTO(Autor autor) {
        return new AutorDTO(autor.getAutor_id(), autor.getNome(), autor.getSobrenome());
    }

    public AutorDTO(UUID autor_id, String nome, String sobrenome) {
        this.autor_id = autor_id;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public AutorDTO() {

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
