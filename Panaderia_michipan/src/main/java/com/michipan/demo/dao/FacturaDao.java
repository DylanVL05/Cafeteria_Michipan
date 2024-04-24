
package com.michipan.demo.dao;

import com.michipan.demo.domain.Factura;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FacturaDao  extends JpaRepository<Factura, Long>{
    
}
