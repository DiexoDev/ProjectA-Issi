package com.diegobpz.Issi.com.service;

import com.diegobpz.Issi.com.entity.Usuario;
import com.diegobpz.Issi.com.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    UsuarioRepository repository;

    @Override
    public List<Usuario> listarTodosLosUsuarios() {
        return repository.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    @Override
    public Usuario obtenerUsuarioPorId(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public Optional<Usuario> encontrarPorEmail(String email) {
        return Optional.empty();
    }

    @Override
    public void eliminarUsuario(Long id) {
        repository.deleteById(id);
    }
}
