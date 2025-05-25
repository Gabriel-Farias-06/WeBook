package com.webook.app.application.DTOs.Response;

public class UsuarioResponse {
    public String email;
    public String senha;

    public UsuarioResponse(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsuarioResponse() {

    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
