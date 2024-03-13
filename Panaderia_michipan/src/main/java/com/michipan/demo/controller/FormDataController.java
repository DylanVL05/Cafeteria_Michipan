/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.michipan.demo.controller;

import com.michipan.demo.domain.FormData;
import com.michipan.demo.service.FormDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/formdata")
public class FormDataController {

    @Autowired
    private FormDataService formDataService;

    @PostMapping("/submit")
    public String submitForm(@RequestBody FormData formData) {
        try {
            formDataService.saveFormData(formData);
            // Puedes devolver un mensaje de éxito o lo que necesites
            return "Formulario enviado exitosamente";
        } catch (Exception e) {
            e.printStackTrace();
            // Puedes devolver un mensaje de error o redirigir a una página de error
            return "Error al procesar el formulario";
        }
    }
}
