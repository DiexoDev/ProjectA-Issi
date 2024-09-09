package com.diegobpz.Issi.com.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "contrasena", nullable = false)
    private String contrasena;
    @Column(name = "telefono", nullable = false)
    private String telefono;
    @Column(name = "fechaNac", nullable = false)
    private LocalDate fechaNacimiento;

    public Usuario(String nombre, String email, String contrasena, String telefono, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }
}
