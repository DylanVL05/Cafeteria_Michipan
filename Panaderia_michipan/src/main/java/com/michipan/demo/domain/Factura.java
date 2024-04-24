package com.michipan.demo.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import lombok.Data;

@Data
@Entity
@Table(name = "factura")
public class Factura implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//La base de datos genera autmaticamente el IdProducto
    @Column(name = "id_factura")
   
    private Long idFactura;
    private Long idUsuario;
    private Date fecha;
    private double total;
    private int estado;

    public Factura() {
        
    }

    public Factura(Long idUsuario) {
        this.idUsuario = idUsuario;
        this.fecha=Calendar.getInstance().getTime();
        this.estado=1;
    }


    
    
    
    

}


/*
create table techshop.producto (
  id_producto INT NOT NULL AUTO_INCREMENT,
  descripcion VARCHAR(30) NOT NULL,
  ruta_imagen varchar(1024),
  activo bool,
  PRIMARY KEY (id_producto))
 */
