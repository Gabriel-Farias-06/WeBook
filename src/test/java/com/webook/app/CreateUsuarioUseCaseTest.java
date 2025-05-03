package com.webook.app;

import com.webook.app.application.UseCase.Usuario.CreateUsuarioUseCase;
import com.webook.app.domain.Entity.Usuario;
import com.webook.app.domain.Exceptions.EmailAlreadyExists;
import com.webook.app.domain.Exceptions.EmailInvalidoException;
import com.webook.app.domain.Exceptions.SenhaInvalidaException;
import com.webook.app.domain.Interfaces.UsuarioRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

public class CreateUsuarioUseCaseTest {

    @Test
    public void shouldCreateUsuarioWhenEmailDontExists() throws EmailInvalidoException, SenhaInvalidaException {
        UsuarioRepository mockRepo = mock(UsuarioRepository.class);
        var usuario = new Usuario(UUID.randomUUID(), "Gabs", "gabriel@gmail.com", "Divs0342*");


        when(mockRepo.findByEmail(usuario.getEmail())).thenThrow(EmailInvalidoException.class);

        var createUsuarioUseCase = new CreateUsuarioUseCase(mockRepo);

        createUsuarioUseCase.execute(usuario);

        verify(mockRepo).findByEmail(usuario.getEmail());
    }

    @Test
    public void shouldThrowExceptionWhenEmailAlreadyExists() throws EmailInvalidoException, SenhaInvalidaException {
        UsuarioRepository mockRepo = mock(UsuarioRepository.class);
        var usuario = new Usuario(UUID.randomUUID(), "Gabs", "gabriel@gmail.com", "Divs0342*");


        when(mockRepo.findByEmail(usuario.getEmail())).thenReturn(Optional.of(usuario));
        when(mockRepo.create(usuario)).thenThrow(new EmailAlreadyExists());

        var createUsuarioUseCase = new CreateUsuarioUseCase(mockRepo);

        createUsuarioUseCase.execute(usuario);

        verify(mockRepo).findByEmail(usuario.getEmail());
    }
}
