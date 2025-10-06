package com.webook.app.application.DTOs;

import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Exceptions.EmailInvalidoException;
import com.webook.app.domain.Exceptions.SenhaInvalidaException;
import com.webook.app.domain.Validators.EmailValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UsuarioDTO {

    private UUID usuario_id;

    private String nome;

    private String email;

    private String caminhoFoto;

    private List<LivroDTO> livros;

    public static UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getUsuario_id(), usuario.getNome(), usuario.getEmail(), usuario.getCaminhoFoto(), usuario.getLivros() == null ? new ArrayList<>() : usuario.getLivros().stream().map(LivroDTO::toDTO).toList());
    }

    public UsuarioDTO(UUID usuario_id, String nome, String email, String caminhoFoto, List<LivroDTO> livros) {
        this.usuario_id = usuario_id;
        this.nome = nome;
        this.email = email;
        this.caminhoFoto = caminhoFoto;
        this.livros = livros;
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

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public List<LivroDTO> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroDTO> livros) {
        this.livros = livros;
    }
}
