package com.michipan.demo.domain;

import jakarta.persistence.*;

import java.io.Serializable;

import lombok.Data;

@Data
@Entity
@Table(name = "tab_granos")
public class Grano implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Cambiado a snake_case para coincidir con el nombre de la columna en la tabla
    @Column(name = "nombre_grano")
    private String nombreGrano;

    private String descripcion;
    private String imagen;

    // Constructor por defecto (puedes personalizar según necesidades)
    public Grano() {
    }

    // Constructor con parámetros
    public Grano(String nombreGrano, String descripcion, String imagen) {
        this.nombreGrano = nombreGrano;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    // Otros métodos, equals, hashCode, toString, etc., según necesidades
}