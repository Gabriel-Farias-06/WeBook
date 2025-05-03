package com.webook.app;

import com.webook.app.domain.Exceptions.EmailInvalidoException;

import com.webook.app.domain.Validators.EmailValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmailValidatorTest {

    @Test
    public void shouldReturnWhenEmailIsValid() throws EmailInvalidoException {
        String email = "gabriel@gmail.com";
        EmailValidator.validarEmail(email);
    }

    @Test
    public void shouldThrowEmailInvalidaExceptionWhenEmailIsInalid() throws EmailInvalidoException {
        String email = "gabrielgmail.com";
        assertThrows(EmailInvalidoException.class, () -> EmailValidator.validarEmail(email));
    }
}
