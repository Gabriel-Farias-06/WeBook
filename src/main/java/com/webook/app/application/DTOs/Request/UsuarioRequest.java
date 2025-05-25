package com.webook.app.application.DTOs.Request;

public class UsuarioRequest {
    public String email;
    public String senha;

    public UsuarioRequest(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public UsuarioRequest() {

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
