package com.webook.app.application.UseCase.Autor;

import com.webook.app.application.DTOs.Response.AutorResponse;
import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Interfaces.AutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateAutorUseCase {

    private final AutorRepository autorRepository;

    public CreateAutorUseCase(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public ResponseEntity<AutorResponse> execute(Autor autor) {
        if(autorRepository.findByNomeAndSobrenome(autor.getNome(), autor.getSobrenome()).isPresent())
            return ResponseEntity.status(400).body(null);
        autorRepository.create(autor);
        return ResponseEntity.ok(new AutorResponse(autor.getNome(), autor.getSobrenome()));
    }
}
