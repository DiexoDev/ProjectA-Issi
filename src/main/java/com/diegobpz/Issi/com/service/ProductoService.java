package com.diegobpz.Issi.com.service;

import com.diegobpz.Issi.com.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public List<Producto> obtenerProductos();

    public Producto guardarUsuario(Producto producto);

    public Producto actualizarUsuario(Producto producto);

    public Producto obtenerProductoPorId(Long id);

    public void eliminarUsuario(Long id);
}
