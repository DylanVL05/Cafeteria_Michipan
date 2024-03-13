package com.michipan.demo.dao;

import com.michipan.demo.domain.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDAO extends JpaRepository<Personal, Long> {
    // Puedes agregar consultas personalizadas si es necesario
}