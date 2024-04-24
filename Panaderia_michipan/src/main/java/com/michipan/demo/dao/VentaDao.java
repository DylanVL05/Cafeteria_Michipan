
package com.michipan.demo.dao;


import com.michipan.demo.domain.Venta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface VentaDao  extends JpaRepository<Venta, Long>{
    
}
