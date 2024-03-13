/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.michipan.demo.service.impl;

import com.michipan.demo.dao.FormDataRepository;
import com.michipan.demo.domain.FormData;
import com.michipan.demo.service.FormDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author andro
 */
@Service
public class FormDataServiceImpl implements FormDataService {

    @Autowired
    private FormDataRepository formDataRepository;

    @Override
    public void saveFormData(FormData formData) {
        formDataRepository.save(formData);
    }
}
