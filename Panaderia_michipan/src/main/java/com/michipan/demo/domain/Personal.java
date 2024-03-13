/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.michipan.demo.domain;

import jakarta.persistence.*;

import java.io.Serializable;

import lombok.Data;

@Data
@Entity
@Table(name = "tab_personal")
public class Personal implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String rol;
    private String frase;
    private String imagen;

    // Constructor por defecto (puedes personalizar según necesidades)
    public Personal() {
    }

    // Constructor con parámetros
    public Personal(String nombre, String rol, String frase, String imagen) {
        this.nombre = nombre;
        this.rol = rol;
        this.frase = frase;
        this.imagen = imagen;
    }

    // Otros métodos, equals, hashCode, toString, etc., según necesidades
}
