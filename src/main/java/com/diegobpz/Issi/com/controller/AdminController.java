package com.diegobpz.Issi.com.controller;

import com.diegobpz.Issi.com.entity.Producto;
import com.diegobpz.Issi.com.repository.ProductoRepository;
import com.diegobpz.Issi.com.service.AssetsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductoRepository repository;
    @Autowired
    private AssetsServiceImpl service;

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




}
