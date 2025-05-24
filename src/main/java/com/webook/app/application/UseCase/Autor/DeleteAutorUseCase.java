package com.webook.app.application.UseCase.Autor;

import com.webook.app.domain.Entity.Autor;
import com.webook.app.domain.Interfaces.AutorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class DeleteAutorUseCase {

    private final AutorRepository autorRepository;

    public DeleteAutorUseCase(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Transactional
    public ResponseEntity<Boolean> execute(String nome, String sobrenome) {
        Optional<Autor> autor = autorRepository.findByNomeAndSobrenome(nome, sobrenome);
        if(autor.isEmpty())
            return ResponseEntity.status(404).body(false);

        autorRepository.delete(autor.get().getAutor_id());
        return ResponseEntity.ok(true);
    }
}
