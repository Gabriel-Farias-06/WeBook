package com.webook.app.application.UseCase.Livro;

import com.webook.app.application.DTOs.Response.LivroResponse;
import com.webook.app.domain.Entity.Livro;
import com.webook.app.domain.Interfaces.LivroRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllLivroUseCase {
    private  final LivroRepository livroRepository;

    public FindAllLivroUseCase(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public ResponseEntity<List<LivroResponse>> execute(){
        return ResponseEntity.ok(livroRepository.findAll().stream().map(LivroResponse::new).toList());
    }
}
