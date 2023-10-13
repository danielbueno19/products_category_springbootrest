package com.aplication.rest.controllers.dto;

import com.aplication.rest.entities.Product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MakerDTO {
    
    private Long id;
    private String name;

    private List<Product> productList = new ArrayList<>();
}

/*
1) El DTO también sirve para encapsular la lógica de serialización o deserialización, que es el mecanismo que convierte el objeto en un formato
   que se puede almacenar o transferir, como JSON o XML.
*/