package com.aplication.rest.persistence;

import com.aplication.rest.entities.Maker;
import com.aplication.rest.entities.Product;

import java.math.BigDecimal;

import java.util.List;
import java.util.Optional;

public interface IProductDAO {
    List<Maker> findAll();

    Optional<Product> findById(Long id);

    void save(Product product);

    void deleteById(Long id);

    List<Product> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice);
}

/*
1) Capa de persistencia, el cual implementa un patron de diseño DAO (object access data) donde definimos la funcionalidad que va a tener la aplicacion, 
   para este caso van a ser funcionalidades de un CRUD.
   Que al ser una interfaz solo defino las funciones
 - findById(Long id): para busquedas por ID.
   findAll(): para listar todo.
   save(Maker maker): guardar.
   deleteById(Long id): borrar
 - Recordar que el Optional es una clase que indica que puede tener o no valor, es mas para evitar valores nulos o errores nullpointer
 - QueryMethod: nos permite crear cnsultas sql como findByPriceInRange()


*/