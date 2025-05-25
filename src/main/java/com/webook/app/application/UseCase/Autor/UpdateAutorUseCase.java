package com.webook.app.application.UseCase.Autor;

import com.webook.app.application.DTOs.Response.AutorResponse;
import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Interfaces.AutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UpdateAutorUseCase {
    private final AutorRepository autorRepository;

    public UpdateAutorUseCase(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public ResponseEntity<AutorResponse> execute(Autor autor){
        if(autorRepository.findByNomeAndSobrenome(autor.getNome(), autor.getSobrenome()).isEmpty())
            return ResponseEntity.status(404).body(null);
        Autor autorAtualizado = autorRepository.update(autor);

        return ResponseEntity.ok(new AutorResponse(autorAtualizado.getNome(), autorAtualizado.getSobrenome()));
    }
}
