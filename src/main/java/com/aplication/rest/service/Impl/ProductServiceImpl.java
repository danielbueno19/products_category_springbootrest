package com.aplication.rest.service.Impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aplication.rest.entities.Product;
import com.aplication.rest.persistence.IProductDAO;
import com.aplication.rest.service.IProductService;

@Service
public class ProductServiceImpl implements IProductService{

    @Autowired
    private IProductDAO iProductDAO;

    @Override
    public List<Product> findAll() {
        return iProductDAO.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return iProductDAO.findById(id);
    }

    @Override
    public void save(Product product) {
        iProductDAO.save(product);
    }

    @Override
    public void deleteById(Long id) {
        iProductDAO.deleteById(id);
    }

    @Override
    public List<Product> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return iProductDAO.findByPriceInRange(minPrice, maxPrice);
    }
    
}

/*
1) clases que implementara las funciones que definimos previamente en las interfaces de service
 - @Service: Con esta notación especificamos que en esta clase se encontrara toda nuestra lógica de negocio, cálculos o llamadas a otras API externas.
 - Hacemos que implement de las interfaces de service
 - Inyectamos la clase que se definio como interface DAO y lo decoramos con @Autowired
   

*/