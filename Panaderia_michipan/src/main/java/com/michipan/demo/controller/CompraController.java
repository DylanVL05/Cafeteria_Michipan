/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.michipan.demo.controller;


import com.michipan.demo.domain.Item;
import com.michipan.demo.service.CategoriaService;
import com.michipan.demo.service.FirebaseStorageService;
import com.michipan.demo.service.ItemService;
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
   private ItemService itemservice;
    @Autowired
    private ProductoService productoservice;

    @Autowired
    private FirebaseStorageService firebaseStorageService;

 @GetMapping("/Ventas2")
    public String listado(Model model) {

        var lista = productoservice.getProductos(false);

        model.addAttribute("productos", lista);

        return "/Ventas2";
    }
    






    

     

}
