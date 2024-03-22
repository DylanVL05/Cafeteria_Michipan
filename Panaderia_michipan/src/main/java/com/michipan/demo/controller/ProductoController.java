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
        var lista = productoService.getProductos();
        model.addAttribute("productos", lista);
        var listaCategorias = categoriaService.getCategorias();
        model.addAttribute("categorias", listaCategorias);
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
        var listaCategorias = categoriaService.getCategorias();
        model.addAttribute("categorias", listaCategorias);
        
        
        
        return "editarProducto";
    }

    
 @PostMapping("/actualizarProducto")
    public String actualizar(@ModelAttribute("producto") Producto producto,
                             @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) { // Se debe subir una imagen
            // Obtener el producto existente por ID
            Producto productoExistente = productoService.getProductoById(producto.getIdProducto());
            
            // Actualizar los campos del producto existente con los valores del formulario
            productoExistente.setDescripcion(producto.getDescripcion());
            productoExistente.setDetalle(producto.getDetalle());
            productoExistente.setPrecio(producto.getPrecio());
            productoExistente.setExistencias(producto.getExistencias());
            
            // Guardar la entidad actualizada en la base de datos
            productoService.saveProducto(productoExistente);

            // Obtener la ruta de la imagen después de guardar el producto
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "producto", productoExistente.getIdProducto());
            productoExistente.setRutaImagen(ruta);

            // Imprimir el valor de la propiedad 'imagen' antes de guardar en la base de datos
            System.out.println("Valor de 'imagen' antes de guardar: " + productoExistente.getRutaImagen());

            // Actualizar la entidad en la base de datos con la ruta de la imagen
            productoService.saveProducto(productoExistente);
        }

        return "redirect:/producto";
    }
     
@PostMapping("/guardarProducto")
public String guardar(Producto producto, @RequestParam("imagenFile") MultipartFile imagenFile) {
    if (!imagenFile.isEmpty()) { // A file must be uploaded
        // First, save the product to obtain the new idProducto
        productoService.saveProducto(producto);

        // Upload the image and get the path
        String ruta = firebaseStorageService.cargaImagen(imagenFile, "producto", producto.getIdProducto());
        
        // Set the image path for the product
        producto.setRutaImagen(ruta);
        
        System.out.println("Valor de 'imagen' antes de guardar: " + producto.getImagen());
        
        
        productoService.saveProducto(producto);
    }

    return "redirect:/producto";
}
}