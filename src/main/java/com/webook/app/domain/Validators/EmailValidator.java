package com.webook.app.domain.Validators;

import com.webook.app.domain.Exceptions.EmailInvalidoException;

public class EmailValidator {
    public static boolean validarEmail(String email) throws EmailInvalidoException {
        if (email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"))
            throw new EmailInvalidoException("Email inváilido");
        return true;
    }
}
