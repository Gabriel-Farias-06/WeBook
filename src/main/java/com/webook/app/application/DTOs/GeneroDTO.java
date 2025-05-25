package com.webook.app.application.DTOs;

import com.webook.app.domain.Entity.Genero;

import java.util.UUID;

public class GeneroDTO {
    private UUID genero_id;

    private String nome;

    public static GeneroDTO toDTO(Genero genero) {
        return new GeneroDTO(genero.getGenero_id(), genero.getNome());
    }

    public GeneroDTO(UUID genero_id, String nome) {
        this.genero_id = genero_id;
        this.nome = nome;
    }

    public GeneroDTO() {

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

}
