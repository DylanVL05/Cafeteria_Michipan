package com.michipan.demo.controller;

import com.michipan.demo.domain.Personal;
import com.michipan.demo.service.FirebaseStorageService;
import com.michipan.demo.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class PersonalController {

    @Autowired
    private PersonalService servicio;

    @GetMapping({"/personal"})
    public String listarPersonals(Model modelo) {
        modelo.addAttribute("personal", servicio.getAllPersonals());
        return "personal";

    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;

    @PostMapping("/guardarPersonal")
    public String guardar(Personal personal, @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) { // Se debe subir una imagen
            servicio.savePersonal(personal);

            String ruta = firebaseStorageService.cargaImagen(imagenFile, "personal", personal.getId());
            personal.setImagen(ruta);

            // Imprime el valor de la propiedad 'imagen' antes de guardar en la base de datos
            System.out.println("Valor de 'imagen' antes de guardar: " + personal.getImagen());

            // Guarda la entidad en la base de datos
            servicio.savePersonal(personal);
        }

        return "redirect:/personal";
    }

// Se deberia usar DeleteMapping una solucion mas Restful pero hay algun tipo de seguridad por defecto pienso que no permite delete
//Se podra tal vez resolver mas adelante con el tema de roles y seguridad
    @GetMapping("/eliminarPersonal/{id}")
    public String elimina(@PathVariable Long id) {
        servicio.deletePersonal(id);
        return "redirect:/personal";
    }

}

/*
    @GetMapping({"/list","/"})
    public ModelAndView listPersonals() {
        ModelAndView modelAndView = new ModelAndView("personals"); // Nombre del archivo HTML
        List<Personal> personals = personalService.getAllPersonals();
        modelAndView.addObject("personals", personals);
        return modelAndView;
    }
}

 */
