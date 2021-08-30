package com.prowaybank.pagamentos.models;


import com.prowaybank.pagamentos.services.ValidarUsuarioService;

import java.math.BigDecimal;

public class PessoaJuridica extends Usuario implements Identificacao {

    private int id_pessoa_juridica;

    private String cnpj;
    private String razao_social;
    private BigDecimal patrimonio;

    private ValidarUsuarioService validarUsuarioService;

    public PessoaJuridica(ValidarUsuarioService validarUsuarioService){
        this.validarUsuarioService = validarUsuarioService;
    }

    public int getId_pessoa_juridica() {
        return id_pessoa_juridica;
    }

    public void setId_pessoa_juridica(int id_pessoa_juridica) {
        this.id_pessoa_juridica = id_pessoa_juridica;
    }

    public String getRazao_social() {
        return razao_social;
    }

    public void setRazao_social(String razao_social) {
        this.razao_social = razao_social;
    }

    public BigDecimal getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(BigDecimal patrimonio) {
        this.patrimonio = patrimonio;
    }

    @Override
    public String getIdentificacao() {
        return cnpj;
    }

    @Override
    public void setIdentificacao(String cnpj) {
        if(this.validarUsuarioService.validarIdentificacaoUsuario(cnpj)){
            this.cnpj = cnpj;
        }
        System.out.println("CNPJ inválido!");
    }

}
