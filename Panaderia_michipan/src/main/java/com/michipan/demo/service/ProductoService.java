/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.michipan.demo.service;

import com.michipan.demo.domain.Producto;
import java.util.List;

/**
 *
 * @author andro
 */
public interface ProductoService {
    /*
    Se define la firma del metodo que recupera la lista de objetos tipo producto de la tabla Producto
     */
    List<Producto> getAllProductos();

    Producto getProductoById(Long idProducto);

    void saveProducto(Producto producto);

    void deleteProducto(Long idProducto);
    
    
    
    
    
    
    
    
}
