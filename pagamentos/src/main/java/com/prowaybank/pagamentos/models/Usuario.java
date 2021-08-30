package com.prowaybank.pagamentos.models;

import java.util.List;

public abstract class Usuario {

    private int usuario_id;

    private String conta;
    private String senha;
    private String nome;
    private String email;
    private String celular;

    //private List<ChavePix> chaves_pix;
    private List<String> cep;

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public List<String> getCep() {
        return cep;
    }

    public void setCep(List<String> cep) {
        this.cep = cep;
    }

}
