package com.webook.app.application.DTOs.Response;

import com.webook.app.application.DTOs.LivroDTO;
import com.webook.app.application.DTOs.UsuarioDTO;
import com.webook.app.domain.Entity.Usuario;

import java.util.List;
import java.util.UUID;

public class UsuarioResponse {
    private UUID usuario_id;

    private String nome;

    private String email;

    private String senha;

    private String caminhoFoto;

    private List<LivroDTO> livros;

    public static UsuarioResponse toDTO(Usuario usuario) {
        return new UsuarioResponse(usuario.getUsuario_id(), usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getCaminhoFoto(), usuario.getLivros().stream().map(LivroDTO::toDTO).toList());
    }

    public UsuarioResponse(UUID usuario_id, String nome, String email, String senha, String caminhoFoto, List<LivroDTO> livros) {
        this.usuario_id = usuario_id;
        this.nome = nome;
        this.livros = livros;
        this.email = email;
        this.senha = senha;
        this.caminhoFoto = caminhoFoto;
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

    public List<LivroDTO> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroDTO> livros) {
        this.livros = livros;
    }
}
