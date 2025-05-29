package com.webook.app.application.UseCase.Genero;

import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Interfaces.GeneroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateGeneroUseCase {

    private final GeneroRepository generoRepository;

    public CreateGeneroUseCase(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    public ResponseEntity<Genero> execute(Genero genero) {
        if(generoRepository.findByNome(genero.getNome()).isPresent())
            return ResponseEntity.status(400).body(null);
        return ResponseEntity.status(200).body(generoRepository.create(genero));
    }
}
