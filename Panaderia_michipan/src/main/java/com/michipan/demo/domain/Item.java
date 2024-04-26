/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.michipan.demo.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author EstivenQ
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends Producto{

    private int cantidad;
    
    public Item(Producto p){
        super.setIdProducto(p.getIdProducto());
        super.setCategoria(p.getCategoria());
        super.setDescripcion(p.getDescripcion());
        super.setDetalle(p.getDetalle());
        super.setPrecio(p.getPrecio());
        super.setExistencias(p.getExistencias());
        super.setActivo(p.isActivo());
        super.setRutaImagen(p.getRutaImagen());
        this.cantidad=0;
    }
    
}
