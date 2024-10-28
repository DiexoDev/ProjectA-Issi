package com.diegobpz.Issi.com.service;

import com.diegobpz.Issi.com.entity.Producto;
import com.diegobpz.Issi.com.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    ProductoRepository repository;

    @Override
    public List<Producto> obtenerProductos() {
        return repository.findAll();
    }

    @Override
    public Producto guardarUsuario(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public Producto actualizarUsuario(Producto producto) {
        return repository.save(producto);
    }

    @Override
    public Producto obtenerProductoPorId(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public void eliminarUsuario(Long id) {
        repository.deleteById(id);
    }
}
