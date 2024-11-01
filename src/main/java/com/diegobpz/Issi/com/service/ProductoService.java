package com.diegobpz.Issi.com.service;

import com.diegobpz.Issi.com.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    public List<Producto> obtenerProductos();

    public Producto guardarProducto(Producto producto);

    public Producto actualizarProducto(Producto producto);

    public Producto obtenerProductoPorId(Long id);

    public void eliminarProducto(Long id);
}
