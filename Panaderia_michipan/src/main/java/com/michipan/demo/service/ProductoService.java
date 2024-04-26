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
    public List<Producto> getProductos(boolean activos);

    /*se define la firma del metodo para recuperar un registro de tabla producto,
    recuperando el registro que tiene el idProducto, si no lo encuentra retorna null
     */
    public Producto getProducto(Producto producto);

    /*se define la firma del metodo para actualizar un registro de tabla producto,
    recuperando el registro que tiene el idProducto, si no lo encuentra retorna null
     */
    public void save(Producto producto);

    /*se define la firma del metodo para eliminar un registro de tabla producto,
     si no lo encuentra retorna null
     */
    void deleteProducto(Long idProducto);

}
