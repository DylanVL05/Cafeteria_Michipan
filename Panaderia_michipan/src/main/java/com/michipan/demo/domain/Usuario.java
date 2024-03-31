
package com.michipan.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;




@Data
@Entity
@Table (name="usuario")
public class Usuario implements  Serializable{
    private static final long serialVersionUID=1L;
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//La base de datos genera autmaticamente el IdCategoria
    @Column(name="id_usuario")
    
    private Long idUsuario;
    private String username;
    private String password;
    private String nombre;
    private String apellidos;
    private String correo;
    private String telefono;
    
    private String rutaImagen;
    private boolean activo;
    
    @OneToMany
    @JoinColumn(name="id_usuario", updatable = false)
    private List<Rol> roles;
       
    
    
    
    
    
}


/* 
create table techshop.categoria (
  id_categoria INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_categoria))
*/
