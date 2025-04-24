package com.webook.app.Infraestructure.Repositories;

import com.webook.app.domain.Entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EditoraJpaRepository extends JpaRepository<Editora, UUID>  {
}
