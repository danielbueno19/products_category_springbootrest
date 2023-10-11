package com.aplication.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.aplication.rest.entities.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long>{
    
}

/*
1) creamos los repositorios para cada clase/entity
 - estas clases van a ser interfaces que heredan de CrudRepository.
   CrudRepository va a recibir <nom_Entity, tipo de dato de la PK de dicha clase> 
   @Repository: puede actuar como una interfaz entre la capa de persistencia y la capa de negocio, y puede implementar operaciones básicas de CRUD


*/