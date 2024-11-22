package com.diegobpz.Issi.com.controller;

import com.diegobpz.Issi.com.entity.Producto;
import com.diegobpz.Issi.com.repository.ProductoRepository;
import com.diegobpz.Issi.com.service.AssetsServiceImpl;
import com.diegobpz.Issi.com.service.ProductoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductoRepository repository;
    @Autowired
    private AssetsServiceImpl service;
    @Autowired
    private ProductoServiceImpl productoService;

    @GetMapping("")
    public ModelAndView verPaginaDeInicio(@PageableDefault(sort = "id") Pageable pageable) {
        Page<Producto> productos = repository.findAll(pageable);
        return new ModelAndView("admin/lista_productos").addObject("productos", productos);
    }

    //Metodo get para mostrar el formulario
    @GetMapping("/producto/nuevo")
    public ModelAndView mostrarFormularioProducto() {
        return new ModelAndView("admin/agregar_producto").addObject("producto", new Producto());
    }

    /*Metodo post para guardar los datos
    BindingResult funciona para capturar y manejar los errores del metodo.*/
    @PostMapping("/producto/nuevo")
    public ModelAndView guardarProducto(@Validated Producto producto, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || producto.getImagen() == null || producto.getImagen().isEmpty()) {
            if (producto.getImagen().isEmpty()) {
                bindingResult.rejectValue("imagen", "MultipartNotEmpty");
            }
            return new ModelAndView("admin/nuevo-producto").addObject("producto", producto);
        }
        String rutaImagen = service.guardarArchivo(producto.getImagen());
        producto.setRutaImagen(rutaImagen);

        repository.save(producto);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/producto/editar/{id}")
    public ModelAndView mostrarFormularioEditarProducto(@PathVariable Long id) {
        Producto productoEncontrado = productoService.obtenerProductoPorId(id);
        return new ModelAndView("admin/editar_producto").addObject("producto", productoEncontrado);
    }

    @PostMapping("/producto/editar/{id}")
    public ModelAndView editarProducto(@PathVariable Long id, @Validated Producto producto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("admin/editar_producto").addObject("producto", producto);
        }
        Producto productoGuardado = productoService.obtenerProductoPorId(id);
        productoGuardado.setNombre(producto.getNombre());
        productoGuardado.setDescripcion(producto.getDescripcion());
        productoGuardado.setPrecio(producto.getPrecio());
        productoGuardado.setStock(producto.getStock());
        productoGuardado.setCategoria(producto.getCategoria());
        productoGuardado.setTalla(producto.getTalla());

        if (!producto.getImagen().isEmpty()) {
            service.eliminarArchivo(productoGuardado.getRutaImagen());
            String rutaImagen = service.guardarArchivo(producto.getImagen());
            productoGuardado.setRutaImagen(rutaImagen);
        }
        productoService.guardarProducto(productoGuardado);
        return new ModelAndView("redirect:/admin");
    }

    @GetMapping("/producto/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        Producto productoEncontrado = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID de producto invalido: " + id));
        service.eliminarArchivo(productoEncontrado.getRutaImagen());
        productoService.eliminarProducto(id);
        return "redirect:/admin";
    }


}
