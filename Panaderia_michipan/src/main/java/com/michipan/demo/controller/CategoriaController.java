package com.michipan.demo.controller;

import com.michipan.demo.domain.Categoria;
import com.michipan.demo.service.CategoriaService;
import com.michipan.demo.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller

public class CategoriaController {

    @Autowired
    private CategoriaService servicio;

    @GetMapping({"/categoria"})
    public String listarCategorias(Model modelo) {
        modelo.addAttribute("categoria", servicio.getCategorias());
        return "categoria";

    }

    @Autowired
    private FirebaseStorageService firebaseStorageService;



// Se deberia usar DeleteMapping una solucion mas Restful pero hay algun tipo de seguridad por defecto pienso que no permite delete
//Se podra tal vez resolver mas adelante con el tema de roles y seguridad
    @GetMapping("/eliminarCategoria/{idCategoria}")
    public String elimina(@PathVariable Long idCategoria) {
        servicio.deleteCategoria(idCategoria);
        return "redirect:/categoria";
    }
    
    @GetMapping("/editarCategoria/{idCategoria}")
public String editar(@PathVariable Long idCategoria, Model modelo) {
    // Obtener la categoría por ID
    Categoria categoria = servicio.getCategoriaById(idCategoria);
    
    // Agregar la categoría al modelo
    modelo.addAttribute("categoria", categoria);
    
    // Devolver la vista de edición
    return "editarCategoria";
}
    
    

   @PostMapping("/actualizarCategoria")
public String actualizar(@ModelAttribute("categoria") Categoria categoria,
                         @RequestParam("imagenFile") MultipartFile imagenFile) {
   if (!imagenFile.isEmpty()) { // Se debe subir una imagen
        // Guarda la entidad en la base de datos
        servicio.saveCategoria(categoria);

        // Obtiene la ruta de la imagen después de guardar la categoría
        String ruta = firebaseStorageService.cargaImagen(imagenFile, "categoria", categoria.getIdCategoria());
        categoria.setRutaImagen(ruta);

        // Imprime el valor de la propiedad 'imagen' antes de guardar en la base de datos
        System.out.println("Valor de 'imagen' antes de guardar: " + categoria.getRutaImagen());

        // Actualiza la entidad en la base de datos con la ruta de la imagen
        servicio.saveCategoria(categoria);
    }

    return "redirect:/categoria";
}
    
    
    
     @PostMapping("/guardarCategoria")
public String guardar(Categoria categoria, @RequestParam("imagenFile") MultipartFile imagenFile) {
    if (!imagenFile.isEmpty()) { // Se debe subir una imagen
        // Guarda la entidad en la base de datos
        servicio.saveCategoria(categoria);

        // Obtiene la ruta de la imagen después de guardar la categoría
        String ruta = firebaseStorageService.cargaImagen(imagenFile, "categoria", categoria.getIdCategoria());
        categoria.setRutaImagen(ruta);

        // Imprime el valor de la propiedad 'imagen' antes de guardar en la base de datos
        System.out.println("Valor de 'imagen' antes de guardar: " + categoria.getRutaImagen());

        // Actualiza la entidad en la base de datos con la ruta de la imagen
        servicio.saveCategoria(categoria);
    }

    return "redirect:/categoria";
}
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
