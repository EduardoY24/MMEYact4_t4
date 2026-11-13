package com.eymm.mmeyact4_t4.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eymm.mmeyact4_t4.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);
}