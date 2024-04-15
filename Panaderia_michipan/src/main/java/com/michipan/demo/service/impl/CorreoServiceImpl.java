/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.michipan.demo.service.impl;

import com.michipan.demo.service.CorreoService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class CorreoServiceImpl implements CorreoService{
    
    @Autowired
    private JavaMailSender mailSender;
    
    @Override
    public void enviarCorreoHtml(String para, String asunto, String contenidoHtml) throws MessagingException {
        MimeMessage correo= mailSender.createMimeMessage();
        MimeMessageHelper apoyo= new MimeMessageHelper(correo, true);
        apoyo.setTo(para);//correo al que se envia
        apoyo.setSubject(asunto);
        apoyo.setText(contenidoHtml, true);
        mailSender.send(correo);
    }
    
}
