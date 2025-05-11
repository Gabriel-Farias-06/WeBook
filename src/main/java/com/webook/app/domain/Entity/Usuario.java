package com.webook.app.domain.Entity;

import com.fasterxml.jackson.annotation.*;
import com.webook.app.domain.Exceptions.EmailInvalidoException;
import com.webook.app.domain.Exceptions.SenhaInvalidaException;
import com.webook.app.domain.Validators.EmailValidator;
import com.webook.app.domain.Validators.SenhaValidator;
import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "usuario_id")
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID usuario_id;

    private String nome;

    private String caminhoFoto;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_livro", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "livro_id"))
    private List<Livro> livros;

    public Usuario(UUID usuario_id, String nome, String caminhoFoto, String email, String senha, List<Livro> livros) throws EmailInvalidoException, SenhaInvalidaException {
        this.usuario_id = usuario_id;
        this.nome = nome;
        this.caminhoFoto = caminhoFoto;
        EmailValidator.validarEmail(email);
        this.email = email;
        SenhaValidator.validarSenha(senha);
        this.senha = senha;
        this.livros = livros;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Usuario(@JsonProperty("usuario_id") UUID usuario_id, @JsonProperty("nome") String nome, @JsonProperty("email") String email, @JsonProperty("senha") String senha) throws EmailInvalidoException, SenhaInvalidaException {
        this.usuario_id = usuario_id;
        this.nome = nome;
        EmailValidator.validarEmail(email);
        this.email = email;
        SenhaValidator.validarSenha(senha);
        this.senha = senha;
    }

    public Usuario() {

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

    public String getCaminhoFoto() {
        return caminhoFoto;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
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

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
