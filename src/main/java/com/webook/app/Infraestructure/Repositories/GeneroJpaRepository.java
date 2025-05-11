package com.webook.app.Infraestructure.Repositories;

import com.webook.app.domain.Entity.Genero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface GeneroJpaRepository extends JpaRepository<Genero, UUID>  {
    Optional<Genero> findByNome(String name);
}
