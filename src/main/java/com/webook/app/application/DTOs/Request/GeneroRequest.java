package com.webook.app.application.DTOs.Request;

public class GeneroRequest {
    public String nome;

    public GeneroRequest(String nome) {
        this.nome = nome;
    }

    public GeneroRequest() {

    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
