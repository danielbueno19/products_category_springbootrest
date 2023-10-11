package com.aplication.rest.persistence.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aplication.rest.entities.Maker;
import com.aplication.rest.persistence.IMakerDAO;
import com.aplication.rest.repository.MakerRepository;

@Component
public class MakerDAOImp implements IMakerDAO{
    @Autowired
    private MakerRepository makerRepository;

    @Override
    public List<Maker> findAll() {
        return (List<Maker>) makerRepository.findAll();
    }

    @Override
    public Optional<Maker> findById(Long id) {
        return makerRepository.findById(id);
    }

    @Override
    public void save(Maker maker) {
        makerRepository.save(maker);
    }

    @Override
    public void deleteById(Long id) {
        makerRepository.deleteById(id);
    }

}

/*
1) clase que implementara las funciones construidas en las clases DAO
 - @Component: con esta anotacion Spring la detecta durante el escaneo de componentes y la registra en el contexto de la aplicación. Esto permite que Spring administre la creación y administración de instancias de esa clase, lo que facilita la inyección de dependencias y la configuración de la aplicación.
 - Inyectamos el repositorio MakerRepository adicionando la anotacion:
   @Autowired
 - Implementamos las funciones del Repository
*/