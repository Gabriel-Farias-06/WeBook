package com.webook.app.application.DTOs.Request;

public class UsuarioUpdateRequest {
    public String nome;
    public String email;
    public String senha;
    public String caminhoFoto;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return nome;
    }
    public void setEmail(String nome) {
        this.nome = nome;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getCaminhoFoto() {
        return caminhoFoto;
    }
    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }
}
