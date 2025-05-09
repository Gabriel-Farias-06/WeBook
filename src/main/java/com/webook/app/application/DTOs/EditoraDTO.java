package com.webook.app.application.DTOs;

import com.webook.app.domain.Entity.Editora;

import java.util.UUID;

public class EditoraDTO {
    private UUID editora_id;

    private String nome;

    public static EditoraDTO toDTO(Editora editora) {
        return new EditoraDTO(editora.getEditora_id(), editora.getNome());
    }

    public EditoraDTO(UUID editora_id, String nome) {
        this.editora_id = editora_id;
        this.nome = nome;
    }

    public EditoraDTO() {

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
}
