package com.michipan.demo.dao;

import com.michipan.demo.domain.Grano;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GranoDAO extends JpaRepository<Grano, Long> {
    // Puedes agregar consultas personalizadas si es necesario
}