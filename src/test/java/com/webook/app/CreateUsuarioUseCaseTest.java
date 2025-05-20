package com.webook.app;

import com.webook.app.application.UseCase.Usuario.CreateUsuarioUseCase;
import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Exceptions.EmailAlreadyExistsException;
import com.webook.app.domain.Exceptions.EmailInvalidoException;
import com.webook.app.domain.Exceptions.SenhaInvalidaException;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CreateUsuarioUseCaseTest {

    @Test
    public void shouldCreateUsuarioWhenEmailDontExists() throws EmailInvalidoException, SenhaInvalidaException, EmailAlreadyExistsException {
        UsuarioRepository mockRepo = mock(UsuarioRepository.class);
        var usuario = new Usuario(UUID.randomUUID(), "Gabs", "gabriel@gmail.com", "Divs0342*");

        when(mockRepo.findByEmail(usuario.getEmail())).thenReturn(Optional.empty());

        var createUsuarioUseCase = new CreateUsuarioUseCase(mockRepo);

        createUsuarioUseCase.execute(usuario);

        verify(mockRepo).findByEmail(usuario.getEmail());
    }

    @Test
    public void shouldThrowExceptionWhenEmailAlreadyExists() throws EmailInvalidoException, SenhaInvalidaException {
        UsuarioRepository mockRepo = mock(UsuarioRepository.class);
        var usuario = new Usuario(UUID.randomUUID(), "Gabs", "gabriel@gmail.com", "Divs0342*");

        when(mockRepo.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));

        var createUsuarioUseCase = new CreateUsuarioUseCase(mockRepo);

        assertThrows(EmailAlreadyExistsException.class, () -> {
            createUsuarioUseCase.execute(usuario);
        });

        verify(mockRepo).findByEmail(usuario.getEmail());
    }
}
