package com.aplication.rest.service;

import com.aplication.rest.entities.Product;

import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;

public interface IProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    void save(Product product);

    void deleteById(Long id);

    List<Product> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice);
}

/*
1) Los service son aquello que contendran la Logica de negocio de la aplicacion
 - definimos las funciones que se crearon el las funciones DAO

*/