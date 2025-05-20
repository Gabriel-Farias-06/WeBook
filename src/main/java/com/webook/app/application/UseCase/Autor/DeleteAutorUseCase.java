package com.webook.app.application.UseCase.Autor;

import com.webook.app.domain.Interfaces.AutorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DeleteAutorUseCase {

    private final AutorRepository autorRepository;

    public DeleteAutorUseCase(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Transactional
    public void execute(UUID id) {
        if(autorRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("Autor com esse ID não foi encontrado");
        autorRepository.delete(id);
    }
}
