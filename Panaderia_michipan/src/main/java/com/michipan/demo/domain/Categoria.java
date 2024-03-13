
package com.michipan.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;





@Data
@Entity
@Table(name = "categoria")
public class Categoria implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCategoria") // Ajusta este nombre según el nombre real de tu columna
    private Long idCategoria;

    @Column(name = "nombre") // Ajusta este nombre según el nombre real de tu columna
    private String nombre;

    @Column(name = "descripcion") // Ajusta este nombre según el nombre real de tu columna
    private String descripcion;

    @Column(name = "rutaImagen") // Ajusta este nombre según el nombre real de tu columna
    private String rutaImagen;

    // Otros campos y métodos de la entidad

    public Categoria() {
    }
    
    
        @OneToMany
    @JoinColumn(name="idCategoria",updatable = false)
    private List<Producto> productos;
    
    
    
    
    
    
    
    
    
    
    
    
    
}

    
    



/* 
create table techshop.categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_categoria))
*/
