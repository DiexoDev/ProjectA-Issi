package com.diegobpz.Issi.com.controller;

import com.diegobpz.Issi.com.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    @GetMapping("/catalogo")
    public ModelAndView mostrarCatalogo(Model model) {
        model.addAttribute("productos", productoService.obtenerProductos());
        return new ModelAndView("catalogo").addObject("productos", productoService.obtenerProductos());
    }
}
