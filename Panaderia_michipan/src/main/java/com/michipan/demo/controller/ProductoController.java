/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.michipan.demo.controller;

import com.michipan.demo.domain.Categoria;
import com.michipan.demo.domain.Producto;
import com.michipan.demo.service.CategoriaService;
import com.michipan.demo.service.ProductoService;
import com.michipan.demo.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProductoController {
    
    
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

  @GetMapping("/producto")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
        model.addAttribute("categorias", categoriaService.getAllCategorias());
        return "producto";
    }


    @GetMapping("/eliminarProducto/{idProducto}")
    public String eliminar(@PathVariable Long idProducto) {
        productoService.deleteProducto(idProducto);
        return "redirect:/producto";
    }

    @GetMapping("/editarProducto/{idProducto}")
    public String editar(@PathVariable Long idProducto, Model model) {
        Producto producto = productoService.getProductoById(idProducto);
        model.addAttribute("producto", producto);
        return "editarProducto";
    }
    
    /*
    @GetMapping("/editarProducto/{idProducto}")
public String editar(@PathVariable Long idProducto, Model model) {
    // Obtener el producto por ID
    Producto producto = productoService.getProductoById(idProducto);
    
    // Obtener la categoría del producto
    Categoria categoria = producto.getCategoria();                       Este metodo es para cuando se obtenga vista de edicion 
    
    // Agregar el producto y la categoría al modelo
    model.addAttribute("producto", producto);
    model.addAttribute("categoria", categoria);
    
    return "editarProducto";
}
    
    */
    

@PostMapping("/guardarProducto")
public String guardar(@ModelAttribute("producto") Producto producto,
                      @RequestParam("imagenFile") MultipartFile imagenFile,
                      @RequestParam("categoriaId") Long categoriaId) {
    
    if (categoriaId != null) {
        
        Categoria categoria = categoriaService.getCategoriaById(categoriaId);

       
        if (categoria != null) {
         
            producto.setCategoria(categoria);
        } else {
        
            throw new CategoriaNotFoundException("La categoría con ID " + categoriaId + " no existe");
        }
    } else {
       
      
     
        throw new IllegalArgumentException("El ID de categoría no puede ser nulo");
    }

 
    if (!imagenFile.isEmpty()) {
        String ruta = firebaseStorageService.cargaImagen(imagenFile, "producto", producto.getIdProducto());
        producto.setRutaImagen(ruta);
    }
    productoService.saveProducto(producto);
    return "redirect:/producto";
}

public class CategoriaNotFoundException extends RuntimeException {
    public CategoriaNotFoundException(String message) {
        super(message);
    }
}










}