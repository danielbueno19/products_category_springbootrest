package com.aplication.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aplication.rest.entities.Product;

import java.util.List;
import java.math.BigDecimal;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{

    List<Product> finProductByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
}

/*
1) creamos los repositorios para cada clase/entity
 - estas clases van a ser interfaces que heredan de CrudRepository.
   CrudRepository va a recibir <nom_Entity, tipo de dato de la PK de dicha clase> 
   @Repository: puede actuar como una interfaz entre la capa de persistencia y la capa de negocio, y puede implementar operaciones básicas de CRUD,
    
2) CrudRepository, provee de todas las funciones de CRUD pero tambien permite crear nuestas propias funciones personalizadas para consultar a la base de datos,
   ya que la anotacion  @Repository Se usa para las clases o interfaces que funcionaran con el acceso a la base de datos.
 - QueryMethod: finProductByPriceBetween()
 - Query: @Query("SELECT p FROM Product p WHERE p.price BETWEEN ?1 AND ?2")
    List<Product> findProductByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice);


*/