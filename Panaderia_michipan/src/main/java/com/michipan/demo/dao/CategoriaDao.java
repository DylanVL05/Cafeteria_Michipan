
package com.michipan.demo.dao;

import com.michipan.demo.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CategoriaDao  extends JpaRepository<Categoria, Long>{
    
}
