
package com.michipan.demo.dao;


import com.michipan.demo.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioDao  extends JpaRepository<Usuario, Long>{
    
    public Usuario findByUsername(String username);
     
    

    
}
     

