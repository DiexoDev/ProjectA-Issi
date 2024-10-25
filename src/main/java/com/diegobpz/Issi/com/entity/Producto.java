package com.diegobpz.Issi.com.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "productos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "nombre")
    private String nombre;
    @NotBlank
    @Column(name = "descripcion")
    private String descripcion;
    @NotNull
    @Column(name = "precio")
    private Double precio;
    @NotNull
    @Column(name = "stock")
    private Integer stock;
    @NotBlank
    @Column(name = "categoria")
    private String categoria;
    @NotBlank
    @Column(name = "talla")
    private String talla;


    private String rutaImagen;

    @Transient
    private MultipartFile imagen;

    public Producto(Long id, @NotBlank String nombre, @NotBlank String descripcion,
                    @NotNull Double precio, @NotBlank Integer stock, @NotBlank String categoria, String rutaImagen,
                    MultipartFile imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.rutaImagen = rutaImagen;
        this.imagen = imagen;
    }

    public Producto(@NotBlank String nombre, @NotBlank String descripcion, @NotNull Double precio,
                    @NotBlank Integer stock, @NotBlank String categoria, String rutaImagen, MultipartFile imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.rutaImagen = rutaImagen;
        this.imagen = imagen;
    }

}
