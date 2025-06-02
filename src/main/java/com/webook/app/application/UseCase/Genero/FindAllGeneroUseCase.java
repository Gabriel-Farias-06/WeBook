package com.webook.app.application.UseCase.Genero;

import com.webook.app.domain.Entity.Genero;
import com.webook.app.domain.Interfaces.GeneroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FindAllGeneroUseCase {
    private final GeneroRepository generoRepository;

    public FindAllGeneroUseCase(GeneroRepository generoRepository){
        this.generoRepository = generoRepository;
    }

    @Transactional
    public ResponseEntity<List<Genero>> execute(){
        return  ResponseEntity.ok(generoRepository.findAll());
    }
}
