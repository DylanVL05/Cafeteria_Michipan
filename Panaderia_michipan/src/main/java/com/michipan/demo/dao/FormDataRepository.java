/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.michipan.demo.dao;
import com.michipan.demo.domain.FormData;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author andro
 */
public interface FormDataRepository extends JpaRepository<FormData, Long> {
    
}