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
@RequestMapping("/producto")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado")
    public String listado(Model model) {

        var lista = productoService.getProductos(false);

        model.addAttribute("productos", lista);

        model.addAttribute("totalProductos", lista.size());

        //aqui se trae la informacion para mostrar las categoria en la lista de productos
        var categorias = categoriaService.getCategorias(true);

        model.addAttribute("categorias", categorias);

        return "/producto/listado";
    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardar")
    public String guardar(Producto producto, @RequestParam("imagenFile") MultipartFile imagenFile) {

        if (!imagenFile.isEmpty()) {// se debe subir una imagen
            //Primero se guarda la producto, para obtener el id producto nuevo
            productoService.save(producto);
            String ruta = firebaseStorageService.cargaImagen(imagenFile, "producto", producto.getIdProducto());
            producto.setRutaImagen(ruta);
        }
        productoService.save(producto);
        return "redirect:/producto/listado";
    }

    @GetMapping("/modificar/{idProducto}")
    public String modifica(Producto producto, Model model) {
        producto = productoService.getProducto(producto);
        model.addAttribute("producto", producto);
        var categorias = categoriaService.getCategorias(false);
        model.addAttribute("categorias", categorias);
        return "producto/modifica";
    }

    @GetMapping("/eliminar/{idProducto}")
    public String elimina(@PathVariable Long idProducto) {
        productoService.deleteProducto(idProducto);
        return "redirect:/producto/listado";
    }
}
