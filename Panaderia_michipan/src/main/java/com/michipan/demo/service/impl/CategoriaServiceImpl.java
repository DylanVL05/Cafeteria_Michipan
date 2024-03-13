
package com.michipan.demo.service.impl;

import com.michipan.demo.dao.CategoriaDao;
import com.michipan.demo.domain.Categoria;
import com.michipan.demo.service.CategoriaService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *

 */
@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaDao categoriaDAO;

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaDAO.findAll();
    }

    @Override
    public Categoria getCategoriaById(Long idCategoria) {
        Optional<Categoria> optionalCategoria = categoriaDAO.findById(idCategoria);
        return optionalCategoria.orElse(null);
    }

    @Override
    public void saveCategoria(Categoria categoria) {
        categoriaDAO.save(categoria);
    }

    @Override
    public void deleteCategoria(Long idCategoria) {
        categoriaDAO.deleteById(idCategoria);
    }
    
    
    
    
    
    
    
    
    
}
