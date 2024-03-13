package com.michipan.demo.service.impl;

import com.michipan.demo.dao.PersonalDAO;
import com.michipan.demo.domain.Personal;
import com.michipan.demo.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    private PersonalDAO personalDAO;

    @Override
    public List<Personal> getAllPersonals() {
        return personalDAO.findAll();
    }

    @Override
    public Personal getPersonalById(Long id) {
        Optional<Personal> optionalPersonal = personalDAO.findById(id);
        return optionalPersonal.orElse(null);
    }

    @Override
    public void savePersonal(Personal personal) {
        personalDAO.save(personal);
    }

    @Override
    public void deletePersonal(Long id) {
        personalDAO.deleteById(id);
    }
}
