package com.michipan.demo.controller;

import com.michipan.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class MenuController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("Menu")
    public String listado(Model model) {

        return "/Menu";
    }

    @GetMapping("nosotros")
    public String nostros(Model model) {

        return "/nosotros";

    }

    @GetMapping("Ubicacion")
    public String ubicacion(Model model) {

        return "/Ubicacion";

    }
    

    
  
}
