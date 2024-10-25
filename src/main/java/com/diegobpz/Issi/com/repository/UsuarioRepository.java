package com.diegobpz.Issi.com.repository;

import com.diegobpz.Issi.com.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    @Query("from Usuario u where u.email = ?1")
    public List<Usuario> findByEmail(String email);

    @Query("from Usuario u where u.email = ?1 and u.contrasena = ?2")
    public Usuario findByEmailAndContrasena(String email, String contrasena);
}
