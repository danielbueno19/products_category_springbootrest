package com.aplication.rest.service;

import com.aplication.rest.entities.Maker;

import java.util.List;
import java.util.Optional;

public interface IMakerService {
    List<Maker> findAll();

    Optional<Maker> findById(Long id);

    void save(Maker maker);

    void deleteById(Long id);
}

/*
1) Los service son aquello que contendran la Logica de negocio de la aplicacion
 - definimos las funciones que se crearon el las funciones DAO

*/