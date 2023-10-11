package com.aplication.rest.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@org.hibernate.annotations.Table(appliesTo = "fabricante")
@Setter @Getter
@Builder
@AllArgsConstructor 
@NoArgsConstructor
@Entity
@Table(name = "fabricante")
public class Maker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String name;

    @OneToMany(mappedBy = "maker", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Product> productList = new ArrayList<>();
}

/* 
1) sera una clase de un fabricante 
 - ya que tenemos las anotaciones de lombok las usaremos para crear los metodos getter y setter, con la siguiente anotacion:
   Setter - Getter
   @Builder: permite especificar atributos que se dean asignar medinate metodos encadenados.
   @AllArgsConstructor: genera un constructor con un parámetro por cada atributo de la clase. Los atributos marcados con @NonNull generan una validación de nulidad en el constructor.
   @NoArgsConstructor: Esta anotación genera un constructor sin parámetros. Si hay atributos finales o con restricciones, se inicializan con valores por defecto (0, false, null). Esta anotación es útil para cumplir con ciertos requisitos de algunos frameworks o librerías que necesitan un constructor vacío.

 - anotaiones JPA  
   @Entity: Le indica a una clase Java que está representando una tabla de nuestra base de datos, por lo que necesariamente debería tener una anotación @Id
   @Table: posee atributos para sobre escribir el nombre de la tabla, por ejemplo: si en la DDBB tabla es fabricante, para trabajar con ella a nivel de codigo podemos reescribirla como: Maker, si no se especifica tomara por defecto la que hayamos definido en dicho campo, es decir: Maker y buscara se nombre de tabla en la DDBB
   @Id: indica que sera la PK
   @GeneratedValue: para que sea JPA quien genere el ID de manera automatica
   @Column(name = "nombre"): buscara o creara en la tabla de la DDBB este atributo: nombre, aun cuando en java se haya establecido private String name;

2) se crea la relacion entre Fabricante y Producto, la cual esta dada por 1:*, un fabricante puede fabricar muchos productos
 - List<Product>: en una lista estariamos obteniendo los productos son fabricados por una empresa
   @OneToMany: indicando que es una relacion de uno a muchos y lo estamos mapeando con mappedBy = "maker", por tanto en la clase Product (producto) debe existir una instancia de Maker con el nombre "maker"


*/