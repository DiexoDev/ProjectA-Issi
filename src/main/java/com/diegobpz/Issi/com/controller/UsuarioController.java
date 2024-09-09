package com.diegobpz.Issi.com.controller;

import com.diegobpz.Issi.com.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("/")
    public String mostrarPrincipal(){
        return "index";
    }
    @GetMapping("/registro")
    public String mostrarRegistro(){
        return "registro";
    }
    @GetMapping("/login")
    public String mostrarLogin(){
        return "login";
    }

}
