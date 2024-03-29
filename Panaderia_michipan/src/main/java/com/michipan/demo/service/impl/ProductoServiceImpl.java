/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.michipan.demo.service.impl;

import com.michipan.demo.dao.ProductoDao;
import com.michipan.demo.domain.Producto;
import com.michipan.demo.service.ProductoService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author andro
 */
@Service
public class ProductoServiceImpl implements ProductoService{
    
    
        @Autowired
    private ProductoDao productoDAO;

    @Override
    public List<Producto> getProductos() {
        return productoDAO.findAll();
    }

    @Override
    public Producto getProductoById(Long idProducto) {
        Optional<Producto> optionalProducto = productoDAO.findById(idProducto);
        return optionalProducto.orElse(null);
    }

@Override
public void saveProducto(Producto producto) {
    // Guardar el producto en la base de datos
    productoDAO.save(producto);

    // Verificar si se asignó un ID al producto
    if (producto.getIdProducto() != null) {
        System.out.println("Producto guardado con ID: " + producto.getIdProducto());
    } else {
        System.out.println("¡El producto no tiene un ID asignado después de guardarse!");
    }
}

    @Override
    public void deleteProducto(Long idProducto) {
        productoDAO.deleteById(idProducto);
    }
    
    
    
    
    
    
    
    
}
