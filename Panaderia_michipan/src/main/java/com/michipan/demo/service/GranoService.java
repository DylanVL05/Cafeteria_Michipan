package com.michipan.demo.service;

import com.michipan.demo.domain.Grano;

import java.util.List;

public interface GranoService {

    List<Grano> getAllGranos();

    Grano getGranoById(Long id);

    void saveGrano(Grano grano);

    void deleteGrano(Long id);
}