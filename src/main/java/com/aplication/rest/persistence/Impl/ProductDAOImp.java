package com.aplication.rest.persistence.Impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aplication.rest.entities.Product;
import com.aplication.rest.persistence.IProductDAO;
import com.aplication.rest.repository.ProductRepository;

@Component
public class ProductDAOImp implements IProductDAO{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.finProductByPriceBetween(minPrice, maxPrice);
    }
    
}

/*
1) clase que implementara las funciones construidas en las clases DAO
 - @Component: con esta anotacion Spring la detecta durante el escaneo de componentes y la registra en el contexto de la aplicación. Esto permite que Spring administre la creación y administración de instancias de esa clase, lo que facilita la inyección de dependencias y la configuración de la aplicación.
 - Inyectamos el repositorio MakerRepository adicionando la anotacion:
   @Autowired
 - Implementamos las funciones del Repository
*/