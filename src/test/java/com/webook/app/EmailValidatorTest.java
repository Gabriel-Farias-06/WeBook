package com.webook.app;

import com.webook.app.domain.Exceptions.EmailInvalidoException;

import com.webook.app.domain.Validators.EmailValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmailValidatorTest {

    @Test
    public void shouldReturnWhenEmailIsValid() throws EmailInvalidoException {
        String email = "gabriel@gmail.com";
        assertDoesNotThrow(() -> EmailValidator.validarEmail(email));
    }

    @Test
    public void shouldThrowEmailInvalidaExceptionWhenEmailIsInalid() {
        String email = "gabrielgmail.com";
        assertEquals("Email inválido", assertThrows(EmailInvalidoException.class, () -> EmailValidator.validarEmail(email)).getMessage());
    }
}
