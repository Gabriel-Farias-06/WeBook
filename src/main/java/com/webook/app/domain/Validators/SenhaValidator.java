package com.webook.app.domain.Validators;

import com.webook.app.domain.Exceptions.SenhaInvalidaException;

import java.util.ArrayList;
import java.util.List;

public class SenhaValidator {
    public static boolean validarSenha(String senha) throws SenhaInvalidaException {

        if (senha.length() < 8)
            throw new SenhaInvalidaException("A senha deve ter pelo menos 8 caracteres.");

        if (!senha.matches(".*[A-Z].*"))
            throw new SenhaInvalidaException("A senha deve conter pelo menos uma letra maiúscula.");

        if (!senha.matches(".*[a-z].*"))
            throw new SenhaInvalidaException("A senha deve conter pelo menos uma letra minúscula.");

        if (!senha.matches(".*[0-9].*"))
            throw new SenhaInvalidaException("A senha deve conter pelo menos um número.");

        if (!senha.matches(".*[!@#$%^&*()_+=\\[\\]{}|;:'\",.<>?/`~\\-].*"))
            throw new SenhaInvalidaException("A senha deve conter pelo menos um caractere especial.");

        if (senha.matches(".*\\s.*"))
            throw new SenhaInvalidaException("A senha não pode conter espaços.");

        return true;
    }

}
