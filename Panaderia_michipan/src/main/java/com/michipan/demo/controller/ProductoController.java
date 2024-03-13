/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.michipan.demo.controller;

import com.michipan.demo.domain.Producto;
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
    private ProductoService productoService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @GetMapping("/producto")
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.getAllProductos());
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

    @PostMapping("/actualizarProducto")
    public String actualizar(@ModelAttribute("producto") Producto producto,
                             @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "producto", producto.getIdProducto());
            producto.setRutaImagen(ruta);
        }
        productoService.saveProducto(producto);
        return "redirect:/producto";
    }

    @PostMapping("/guardarProducto")
    public String guardar(@ModelAttribute("producto") Producto producto,
                          @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "producto", null); // No hay ID aún
            producto.setRutaImagen(ruta);
        }
        productoService.saveProducto(producto);
        return "redirect:/producto";
    }
}
