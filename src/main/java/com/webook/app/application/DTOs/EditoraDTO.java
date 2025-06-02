package com.webook.app.application.DTOs;

import com.webook.app.domain.Entity.Editora;

import java.util.UUID;

public class EditoraDTO {

    private String nome;

    public static EditoraDTO toDTO(Editora editora) {
        return new EditoraDTO(editora.getNome());
    }

    public EditoraDTO(String nome) {
        this.nome = nome;
    }

    public EditoraDTO() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
