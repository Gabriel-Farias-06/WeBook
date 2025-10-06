package com.webook.app.application.UseCase.Genero;

import com.webook.app.application.DTOs.GeneroDTO;
import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Interfaces.GeneroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class FindByIdGeneroUseCase {

    private final GeneroRepository generoRepository;

    public FindByIdGeneroUseCase(GeneroRepository generoRepository){ this.generoRepository = generoRepository; }

    public ResponseEntity<GeneroDTO> execute(UUID id){
        Optional<Genero> genero = generoRepository.findById(id);
        return genero.map(value -> ResponseEntity.ok(GeneroDTO.toDTO(value))).orElseGet(() -> ResponseEntity.status(404).body(null));
    }
}
