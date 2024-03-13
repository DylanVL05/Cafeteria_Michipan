/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.michipan.demo.service;

import com.michipan.demo.domain.Categoria;
import java.util.List;

/**
 *
 * @author andro
 */
public interface CategoriaService {
    List<Categoria> getAllCategorias();

    Categoria getCategoriaById(Long idCategoria);

    void saveCategoria(Categoria categoria);

    void deleteCategoria(Long idCategoria);
    
    
    
}
