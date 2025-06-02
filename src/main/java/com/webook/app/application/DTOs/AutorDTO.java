package com.webook.app.application.DTOs;

import com.webook.app.domain.Entity.Autor;

import java.util.UUID;

public class AutorDTO {

    private String nome;

    private String sobrenome;

    public static AutorDTO toDTO(Autor autor) {
        return new AutorDTO(autor.getNome(), autor.getSobrenome());
    }

    public AutorDTO(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public AutorDTO() {

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
