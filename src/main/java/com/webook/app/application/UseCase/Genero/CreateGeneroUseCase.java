package com.webook.app.application.UseCase.Genero;

import com.webook.app.application.DTOs.GeneroDTO;
import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Interfaces.GeneroRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateGeneroUseCase {

    private final GeneroRepository generoRepository;

    public CreateGeneroUseCase(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @Transactional
    public ResponseEntity<GeneroDTO> execute(Genero genero) {
        if(generoRepository.findByNome(genero.getNome()).isPresent())
            return ResponseEntity.status(400).body(null);
        return ResponseEntity.status(200).body(GeneroDTO.toDTO(generoRepository.create(genero)));
    }
}
