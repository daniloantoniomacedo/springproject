package com.prowaybank.pagamentos.models;

import com.prowaybank.pagamentos.services.ValidarUsuarioService;
import org.springframework.stereotype.Component;


import java.math.BigDecimal;

@Component
public class PessoaFisica extends Usuario implements Identificacao{

    private Long id_pessoa_fisica;
    private String cpf;
    private BigDecimal renda_mensal;

    private ValidarUsuarioService validarUsuarioService;

    public PessoaFisica(ValidarUsuarioService validarUsuarioService){
        this.validarUsuarioService = validarUsuarioService;
    }

    public Long getId_pessoa_fisica() {
        return id_pessoa_fisica;
    }

    public void setId_pessoa_fisica(Long id_pessoa_fisica) {
        this.id_pessoa_fisica = id_pessoa_fisica;
    }

    public BigDecimal getRenda_mensal() {
        return renda_mensal;
    }

    public void setRenda_mensal(BigDecimal renda_mensal) {
        this.renda_mensal = renda_mensal;
    }

    @Override
    public String getIdentificacao() {
        return cpf;
    }

    @Override
    public void setIdentificacao(String cpf) {
        if(this.validarUsuarioService.validarIdentificacaoUsuario(cpf)){
            this.cpf = cpf;
        }
        System.out.println("CPF inválido!");
    }

}
