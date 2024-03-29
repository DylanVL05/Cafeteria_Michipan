/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.michipan.demo.controller;


import com.michipan.demo.service.CategoriaService;
import com.michipan.demo.service.FirebaseStorageService;
import com.michipan.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


/**
 *
 * @author andro
 */
@Controller
public class CompraController {
    
    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @GetMapping("/Ordenar")
    public String listarProductos(Model model) {
        var lista = productoService.getProductos();
        model.addAttribute("productos", lista);
        var listaCategorias = categoriaService.getCategorias();
        model.addAttribute("categorias", listaCategorias);
        return "Ordenar/fragmentos";
    }
    






    

     

}
