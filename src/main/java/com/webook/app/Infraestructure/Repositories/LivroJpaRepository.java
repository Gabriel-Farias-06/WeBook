package com.webook.app.Infraestructure.Repositories;

import com.webook.app.domain.Entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface LivroJpaRepository extends JpaRepository<Livro, UUID>  {
    Optional<Livro> findByIsbn(String isbn);
    @NonNull
    List<Livro> findAll();
}
