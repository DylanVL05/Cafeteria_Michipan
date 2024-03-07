package com.michipan.demo.controller;

import com.michipan.demo.domain.Grano;
import com.michipan.demo.service.FirebaseStorageService;
import com.michipan.demo.service.GranoService;
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

public class GranoController {

    @Autowired
    private GranoService servicio;

     @GetMapping({"/granos","/"})
     public String listarGranos(Model modelo){
         modelo.addAttribute("granos",servicio.getAllGranos());
         return "granos"; 
         
         
         
     }
     
     
    @Autowired
    private FirebaseStorageService firebaseStorageService;
    
@PostMapping("/guardar")
public String guardar(Grano grano, @RequestParam("imagenFile") MultipartFile imagenFile) {
    if (!imagenFile.isEmpty()) { // Se debe subir una imagen
        servicio.saveGrano(grano);

        String ruta = firebaseStorageService.cargaImagen(imagenFile, "grano", grano.getId());
        grano.setImagen(ruta);

        // Imprime el valor de la propiedad 'imagen' antes de guardar en la base de datos
        System.out.println("Valor de 'imagen' antes de guardar: " + grano.getImagen());

        // Guarda la entidad en la base de datos
        servicio.saveGrano(grano);
    }

    return "redirect:/granos";
}

// Se deberia usar DeleteMapping una solucion mas Restful pero hay algun tipo de seguridad por defecto pienso que no permite delete
//Se podra tal vez resolver mas adelante con el tema de roles y seguridad

    @GetMapping("/eliminar/{id}")
    public String elimina(@PathVariable Long id) {
        servicio.deleteGrano(id);
        return "redirect:/granos";
    }








}






    
    /*
    @GetMapping({"/list","/"})
    public ModelAndView listGranos() {
        ModelAndView modelAndView = new ModelAndView("granos"); // Nombre del archivo HTML
        List<Grano> granos = granoService.getAllGranos();
        modelAndView.addObject("granos", granos);
        return modelAndView;
    }
}

*/
