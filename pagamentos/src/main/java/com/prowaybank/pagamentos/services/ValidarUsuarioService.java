package com.prowaybank.pagamentos.services;

import com.prowaybank.pagamentos.models.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

public interface ValidarUsuarioService {


    Boolean validarIdentificacaoUsuario(String identificacao);
}
