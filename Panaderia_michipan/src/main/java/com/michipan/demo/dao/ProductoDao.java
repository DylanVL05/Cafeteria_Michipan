
package com.michipan.demo.dao;

import com.michipan.demo.domain.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoDao  extends JpaRepository<Producto, Long>{
    
}
