package com.prowaybank.pagamentos.services;

import org.springframework.stereotype.Service;

@Service
public class ValidarUsuarioServiceImpl implements ValidarUsuarioService{

    public Boolean validarIdentificacaoUsuario(String identificacao){
        return (Validacoes.validarCpf(identificacao) || Validacoes.validarCnpj(identificacao));
    }

//    public Boolean validarIdentificacaoUsuario(Usuario usuario){
//        String identificacao = ((Identificacao) usuario).getIdentificacao();
//        return (Validacoes.validarCpf(identificacao) || Validacoes.validarCnpj(identificacao));
//    }
}
