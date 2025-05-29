package com.webook.app.application.UseCase.Autor;

import com.webook.app.application.DTOs.Response.AutorResponse;
import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Interfaces.AutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CreateAutorUseCase {

    private final AutorRepository autorRepository;

    public CreateAutorUseCase(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public ResponseEntity<Autor> execute(Autor autor) {
        Optional<Autor> autorOptional =  autorRepository.findByNomeAndSobrenome(autor.getNome(), autor.getSobrenome());
        if(autorOptional.isPresent())
            return ResponseEntity.status(200).body(autorOptional.get());
        Autor newAutor = autorRepository.create(autor);
        return ResponseEntity.status(201).body(newAutor);
    }
}
