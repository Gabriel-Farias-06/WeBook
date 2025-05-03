package com.webook.app.Infraestructure.Repositories;

import com.webook.app.domain.Entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface AutorJpaRepository extends JpaRepository<Autor, UUID>  {
    Optional<Autor> findByName(String name);
}
