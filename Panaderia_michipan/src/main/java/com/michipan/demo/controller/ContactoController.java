/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.michipan.demo.controller;

import com.michipan.demo.domain.FormData;
import com.michipan.demo.service.FormDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author andro
 */


@Controller
public class ContactoController {

    @Autowired
    private FormDataService formDataService;

    @GetMapping("/contacto")
    public String mostrarEjemplo(Model model) {
        FormData formData = new FormData();
        model.addAttribute("formData", formData);

        return "contacto";
    }

    @PostMapping("/contacto")
    public String procesarFormulario(@ModelAttribute FormData formData, Model model) {
        try {
            // Guardar en la base de datos utilizando el servicio
            formDataService.saveFormData(formData);

            // Resto del código...

            // Puedes redirigir a otra página o devolver la misma página con algún mensaje de éxito
            return "redirect:/contacto";  // Reemplaza con la ruta a la que deseas redirigir
        } catch (Exception e) {
            // Manejar errores aquí (puedes redirigir a una página de error)
            model.addAttribute("error", "Error al procesar el formulario");
            return "error";  // Reemplaza con la ruta a la página de error
        }
    }
}

    
    
    
    
    
    
    
    
