package com.michipan.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name = "producto")
public class Producto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    private Long idCategoria;
    private String descripcion;
    private String detalle;
    private double precio;
    private int existencias;
    private String rutaImagen;


    @ManyToOne
    @JoinColumn(name = "idCategoria", insertable = false, updatable = false)
    private Categoria categoria;

}

/**
 *
 *
 *
 * create table techshop.producto ( id_producto INT NOT NULL AUTO_INCREMENT,
 * id_categoria INT NOT NULL, descripcion VARCHAR(30) NOT NULL, detalle
 * VARCHAR(1600) NOT NULL, precio double, existencias int, ruta_imagen
 * varchar(1024), activo bool, PRIMARY KEY (id_producto), foreign key
 * fk_producto_caregoria (id_categoria) references categoria(id_categoria) )
 *
 *
 *
 *
 *
 */
