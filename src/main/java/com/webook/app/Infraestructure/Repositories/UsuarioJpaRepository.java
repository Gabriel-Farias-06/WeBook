package com.webook.app.Infraestructure.Repositories;

import com.webook.app.domain.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UsuarioJpaRepository extends JpaRepository<Usuario, UUID>  {
    Optional<Usuario> findByEmail(String email);
}
