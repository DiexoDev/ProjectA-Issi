package com.diegobpz.Issi.com.service;

import com.diegobpz.Issi.com.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    public List<Usuario> listarTodosLosUsuarios();

    public Usuario guardarUsuario(Usuario usuario);

    public Usuario actualizarUsuario(Usuario usuario);

    public Usuario obtenerUsuarioPorId(Long id);

    public Optional<Usuario> encontrarPorEmail(String email);

    public void eliminarUsuario(Long id);
}
