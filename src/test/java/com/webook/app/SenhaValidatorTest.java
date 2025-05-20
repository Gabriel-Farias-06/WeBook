package com.webook.app;

import com.webook.app.domain.Exceptions.SenhaInvalidaException;
import com.webook.app.domain.Validators.SenhaValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SenhaValidatorTest {

    @Test
    public void shouldWorkValidationWhenSenhaIsValid() {
        String senha = "Dksf3256*";
        assertDoesNotThrow( () -> SenhaValidator.validarSenha(senha));
    }

    @Test
    public void shouldThrowSenhaInvalidaExceptionWhenSenhaDontContainsEigthCharactersOrMore() {
        String senha = "Bjd943*";
        assertEquals("A senha deve ter pelo menos 8 caracteres.", assertThrows(SenhaInvalidaException.class, () -> SenhaValidator.validarSenha(senha)).getMessage());
    }

    @Test
    public void shouldThrowSenhaInvalidaExceptionWhenSenhaDontContainsUppercaseLetter() {
        String senha = "bjds943*";
        assertEquals("A senha deve conter pelo menos uma letra maiúscula.", assertThrows(SenhaInvalidaException.class, () -> SenhaValidator.validarSenha(senha)).getMessage());
    }

    @Test
    public void shouldThrowSenhaInvalidaExceptionWhenSenhaDontContainsLowercaseLetter() {
        String senha = "BKSA943*";
        assertEquals("A senha deve conter pelo menos uma letra minúscula.", assertThrows(SenhaInvalidaException.class, () -> SenhaValidator.validarSenha(senha)).getMessage());
    }

    @Test
    public void shouldThrowSenhaInvalidaExceptionWhenSenhaDontContainsNumber() {
        String senha = "Bjdsdfs*";
        assertEquals("A senha deve conter pelo menos um número.", assertThrows(SenhaInvalidaException.class, () -> SenhaValidator.validarSenha(senha)).getMessage());
    }

    @Test
    public void shouldThrowSenhaInvalidaExceptionWhenSenhaDontContainsSpecialCharacters() {
        String senha = "Bjds6534";
        assertEquals("A senha deve conter pelo menos um caractere especial.", assertThrows(SenhaInvalidaException.class, () -> SenhaValidator.validarSenha(senha)).getMessage());
    }

    @Test
    public void shouldThrowSenhaInvalidaExceptionWhenSenhaContainsSpaces() {
        String senha = "Bjds476 *";
        assertEquals("A senha não pode conter espaços.", assertThrows(SenhaInvalidaException.class, () -> SenhaValidator.validarSenha(senha)).getMessage());
    }
}
