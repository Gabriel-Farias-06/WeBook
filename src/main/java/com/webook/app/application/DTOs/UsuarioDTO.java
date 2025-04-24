package com.webook.app.application.DTOs;

import com.webook.app.domain.Exceptions.EmailInvalidoException;
import com.webook.app.domain.Exceptions.SenhaInvalidaException;
import com.webook.app.domain.Validators.EmailValidator;

import java.util.UUID;

public class UsuarioDTO {

    private UUID usuario_id;

    private String nome;

    private String email;

    public UsuarioDTO(UUID usuario_id, String nome, String email) throws EmailInvalidoException, SenhaInvalidaException {
        this.usuario_id = usuario_id;
        this.nome = nome;
        EmailValidator.validarEmail(email);
        this.email = email;
    }

    public UUID getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(UUID usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
