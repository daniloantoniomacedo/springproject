package com.prowaybank.pagamentos.services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validacoes {

    /**
     * Valida CPF de acordo com o digito verificador.
     * @param cpf cpf do usuário.
     * @return boolean true se o cpf é válido e false se inválido.
     */
    public static boolean validarCpf(String cpf) {

        cpf = cpf.replace("-", "").replace(".", "").replace("/", "").trim();

        if (cpf.length() != 11 ||
                cpf.equals("00000000000") || 
                cpf.equals("11111111111") ||
                cpf.equals("22222222222") ||
                cpf.equals("33333333333") ||
                cpf.equals("44444444444") ||
                cpf.equals("55555555555") ||
                cpf.equals("66666666666") ||
                cpf.equals("77777777777") ||
                cpf.equals("88888888888") ||
                cpf.equals("99999999999")) {

            return false;
        }

        int soma = 0;
        int resto;
        for (int i = 1; i <= 9; i++) {
            soma = soma + Integer.parseInt(cpf.substring(i - 1, i)) * (11 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) {
            resto = 0;
        }
        if (resto != Integer.parseInt(cpf.substring(9, 10))) {
            return false;
        }
        soma = 0;
        for (int i = 1; i <= 10; i++) {
            soma = soma + Integer.parseInt(cpf.substring(i - 1, i)) * (12 - i);
        }
        resto = (soma * 10) % 11;
        if (resto == 10 || resto == 11) {
            resto = 0;
        }
        if (resto != Integer.parseInt(cpf.substring(10, 11))) {
            return false;
        }

        return true;

    }

    /**
     * Valida CNPJ de acordo com o digito verificador.
     * @param cnpj cnpj do usuário.
     * @return boolean true se o ncpj é válido e false se inválido.
     */
    public static boolean validarCnpj(String cnpj) {

        cnpj = cnpj.replace("-", "").replace(".", "").replace("/", "").trim();

        // considera-se erro cnpj's formados por uma sequencia de numeros iguais
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111") ||
                cnpj.equals("22222222222222") || cnpj.equals("33333333333333") ||
                cnpj.equals("44444444444444") || cnpj.equals("55555555555555") ||
                cnpj.equals("66666666666666") || cnpj.equals("77777777777777") ||
                cnpj.equals("88888888888888") || cnpj.equals("99999999999999") ||
                (cnpj.length() != 14))
            return(false);

        char dig13, dig14;
        int sm, i, r, num, peso;

        // "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i=11; i>=0; i--) {
                // converte o i-ésimo caractere do cnpj em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (int)(cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else dig13 = (char)((11-r) + 48);

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i=12; i>=0; i--) {
                num = (int)(cnpj.charAt(i)- 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char)((11-r) + 48);

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    /**
     * Verifica se um número é maior ou igual a zero.
     * @param num número informado.
     * @return boolean true se o valor testado é maior ou igual a zero e
     * false se menor que zero.
     */
    public static boolean verificarMaiorOuIgualZero(BigDecimal num){
        if(num.compareTo(new BigDecimal("0.00")) == -1){
            return false;
        }
        return true;
    }

    /**
     * Verifica se uma chave pix possui comprimento de 32 caracteres.
     * @param chavePix chave PIX do usuário.
     * @return true or false
     */
    public static boolean verificarChavePix(String chavePix){
        chavePix = chavePix.replace("-", "").replace(".", "").replace("/", "").trim();
        if(chavePix.length() == 32){
            return true;
        }
        return false;
    }

    /**
     * Valida telefone, considerando DDD, digito 9 é opicional e o comprimento total do telefone também é avaliado.
     * @param telefone telefone do usuário.
     * @return verdadeiro se o telefone é válido, falso caso contrário.
     */
    public static boolean validarTelefone(String telefone){

        telefone = telefone.replace("(", "").replace(")", "").replace(" ", "").replace("-", "");

        /*
        PATTERN:
        ^ = Início da string.
        [1-9]{2} = Dois dígitos de 1 a 9. Não existem códigos de DDD com o dígito 0.
        [9]{0,1} = O primeiro dígito é 9, mais ele pode ou não existir daí o "0" ou "1" dentro da {0,1}.
        [6-9]{1} = o segundo dígito pode ser de 6 à 9.
        [0-9]{3} = Os três outros dígitos são de 0 à 9.
        [0-9]{4} = A segunda metade do número do telefone.
        $ = Final da string.*/

        final Pattern p = Pattern.compile("^[1-9]{2}[9]{0,1}[6-9]{1}[0-9]{3}[0-9]{4}$");
        Matcher m = p.matcher(telefone);
        return m.matches();
    }

    /**
     * Verifica se o email possui o caracter "@" e pelo menos 3 caracteres.
     * @param email email do usuário.
     * @return verdadeiro se o email é válido, falso caso contrário.
     */
    public static boolean validarEmail(String email){
        return email.contains("@") && email.trim().length() >= 3;
    }

    /**
     * Valida a data, verificando se é posterior a data atual do sistema operacional.
     * @param date data a ser validada.
     * @return verdadeira se a data é válida, falso caso contrário.
     */
    public static boolean validarData(Date date){
        Date today = new Date();
        return date.compareTo(today) >= 0;
    }
}
