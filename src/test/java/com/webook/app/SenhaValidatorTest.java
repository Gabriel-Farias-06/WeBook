package com.webook.app;

import com.webook.app.domain.Exceptions.SenhaInvalidaException;
import com.webook.app.domain.Validators.SenhaValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SenhaValidatorTest {

    @Test
    public void shouldWorkValidationWhenSenhaIsValid() throws SenhaInvalidaException {
        String senha = "Dksf3256*";
        SenhaValidator.validarSenha(senha);
    }

    @Test
    public void shouldThrowSenhaInvalidaExceptionWhenSenhaDontContainsEigthCharactersOrMore() {
        String senha = "Bjd943*";
        assertThrows(SenhaInvalidaException.class, () -> SenhaValidator.validarSenha(senha));
    }

    @Test
    public void shouldThrowSenhaInvalidaExceptionWhenSenhaDontContainsUppercaseLetter() {
        String senha = "bjds943*";
        assertThrows(SenhaInvalidaException.class, () -> SenhaValidator.validarSenha(senha));
    }

    @Test
    public void shouldThrowSenhaInvalidaExceptionWhenSenhaDontContainsLowercaseLetter() {
        String senha = "BKSA943*";
        assertThrows(SenhaInvalidaException.class, () -> SenhaValidator.validarSenha(senha));
    }

    @Test
    public void shouldThrowSenhaInvalidaExceptionWhenSenhaDontContainsNumber() {
        String senha = "Bjdsdfs*";
        assertThrows(SenhaInvalidaException.class, () -> SenhaValidator.validarSenha(senha));
    }

    @Test
    public void shouldThrowSenhaInvalidaExceptionWhenSenhaDontContainsSpecialCharacters() {
        String senha = "Bjds6534";
        assertThrows(SenhaInvalidaException.class, () -> SenhaValidator.validarSenha(senha));
    }

    @Test
    public void shouldThrowSenhaInvalidaExceptionWhenSenhaContainsSpaces() {
        String senha = "Bjds476 *";
        assertThrows(SenhaInvalidaException.class, () -> SenhaValidator.validarSenha(senha));
    }
}
