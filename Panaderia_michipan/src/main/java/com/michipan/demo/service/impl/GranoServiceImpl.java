package com.michipan.demo.service.impl;

import com.michipan.demo.dao.GranoDAO;
import com.michipan.demo.domain.Grano;
import com.michipan.demo.service.GranoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GranoServiceImpl implements GranoService {

    @Autowired
    private GranoDAO granoDAO;

    @Override
    public List<Grano> getAllGranos() {
        return granoDAO.findAll();
    }

    @Override
    public Grano getGranoById(Long id) {
        Optional<Grano> optionalGrano = granoDAO.findById(id);
        return optionalGrano.orElse(null);
    }

    @Override
    public void saveGrano(Grano grano) {
        granoDAO.save(grano);
    }

    @Override
    public void deleteGrano(Long id) {
        granoDAO.deleteById(id);
    }
}
