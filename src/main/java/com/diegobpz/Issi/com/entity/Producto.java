package com.diegobpz.Issi.com.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter @ToString
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "precio", nullable = false)
    private Double precio;
    @Column(name = "stock", nullable = false)
    private Integer stock;
    @Column(name = "categoria", nullable = false)
    private String categoria;

    public Producto(String nombre, Double precio, Integer stock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }
}
