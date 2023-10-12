package com.aplication.rest.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplication.rest.entities.Maker;
import com.aplication.rest.persistence.IMakerDAO;
import com.aplication.rest.service.IMakerService;

@Service
public class MakeServiceImpl implements IMakerService{

    @Autowired
    private IMakerDAO iMakerDAO;

    @Override
    public List<Maker> findAll() {
        return iMakerDAO.findAll();
    }

    @Override
    public Optional<Maker> findById(Long id) {
        return iMakerDAO.findById(id);
    }

    @Override
    public void save(Maker maker) {
        iMakerDAO.save(maker);
    }

    @Override
    public void deleteById(Long id) {
        iMakerDAO.deleteById(id);
    }
    
}

/*
1) clases que implementara las funciones que definimos previamente en las interfaces de service
 - @Service: Con esta notación especificamos que en esta clase se encontrara toda nuestra lógica de negocio, cálculos o llamadas a otras API externas.
 - Hacemos que implement de las interfaces de service
 - Inyectamos la clase que se definio como interface DAO y lo decoramos con @Autowired
   

*/