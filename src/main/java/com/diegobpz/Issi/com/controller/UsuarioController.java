package com.diegobpz.Issi.com.controller;

import com.diegobpz.Issi.com.entity.Usuario;
import com.diegobpz.Issi.com.repository.UsuarioRepository;
import com.diegobpz.Issi.com.service.UsuarioServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("api")
public class UsuarioController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioServiceImpl usuarioService;


    @GetMapping("/index")
    public String mostrarIndex() {
        return "index";
    }

    @GetMapping("/registro")
    public String mostrarRegistroForm(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "registro";
    }
    @PostMapping("/registro")
    public String guardarUsuario(@ModelAttribute("usuario") Usuario usuario, Model model){
        usuarioRepository.save(usuario);
        model.addAttribute("usuario", usuario);
        return "redirect:/api/login";
    }

    @PostMapping("/login")
    public String loguearUsuario(@RequestParam("email") String email, @RequestParam("contrasena") String contrasena, HttpSession session, Model model){
        Usuario usuarioEncontrado = usuarioRepository.findByEmailAndContrasena(email, contrasena);
        if (usuarioEncontrado != null){
            String emailUsuario = usuarioEncontrado.getEmail();
            String contrasenaUsuario = usuarioEncontrado.getContrasena();
            if (email.equalsIgnoreCase(emailUsuario) && contrasena.equalsIgnoreCase(contrasenaUsuario)){
                session.setAttribute("usuario", usuarioEncontrado);
                return "redirect:/api/index";
            }
            else {
                model.addAttribute("error", "Usuario o contraseña incorrectos");
                return "redirect:/api/login";
            }
        } else {
            model.addAttribute("error", "Usuario no encontrado");
            return "redirect:/api/login";
        }

    }

    @GetMapping("/login")
    public String mostrarLoginForm(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("usuario", usuario);
        return "login";
    }





}
