package com.webook.app.domain.Validators;

import com.webook.app.domain.Exceptions.EmailInvalidoException;

public class EmailValidator {
    public static boolean validarEmail(String email) throws EmailInvalidoException {
        if (!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*(?:\\.[a-zA-Z]{2,})$"))
            throw new EmailInvalidoException("Email inválido");
        return true;
    }
}
