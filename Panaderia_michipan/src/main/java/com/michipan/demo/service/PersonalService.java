/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.michipan.demo.service;

import com.michipan.demo.domain.Personal;

import java.util.List;

import com.michipan.demo.domain.Personal;
import java.util.List;

public interface PersonalService {

    List<Personal> getAllPersonals();

    Personal getPersonalById(Long id);

    void savePersonal(Personal personal);

    void deletePersonal(Long id);
}