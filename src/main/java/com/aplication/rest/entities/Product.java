package com.aplication.rest.entities;

import java.math.BigDecimal;

import javax.persistence.*;

import lombok.*;

@org.hibernate.annotations.Table(appliesTo = "producto")
@Setter @Getter
@Builder
@AllArgsConstructor 
@NoArgsConstructor
@Entity
@Table(name = "producto")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nombre")
    private String name;
    @Column(name = "precio")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "id_fabricante", nullable = false)
    private Maker maker;
}

/*
1) creacion de la clase Producto
 - ya que tenemos las anotaciones de lombok las usaremos para crear los metodos getter y setter, con la siguiente anotacion:
   Setter - Getter
   @Builder: permite especificar atributos que se dean asignar medinate metodos encadenados.
   @AllArgsConstructor: genera un constructor con un parámetro por cada atributo de la clase. Los atributos marcados con @NonNull generan una validación de nulidad en el constructor.
   @NoArgsConstructor: Esta anotación genera un constructor sin parámetros. Si hay atributos finales o con restricciones, se inicializan con valores por defecto (0, false, null). Esta anotación es útil para cumplir con ciertos requisitos de algunos frameworks o librerías que necesitan un constructor vacío.

 - anotaiones JPA  
   @Entity: Le indica a una clase Java que está representando una tabla de nuestra base de datos, por lo que necesariamente debería tener una anotación @Id
   @Table: posee atributos para sobre escribir el nombre de la tabla, por ejemplo: si en la DDBB tabla es fabricante, para trabajar con ella a nivel de codigo podemos reescribirla como: Maker, si no se especifica tomara por defecto la que hayamos definido en dicho campo, es decir: Maker y buscara se nombre de tabla en la DDBB
   @Id: indica que sera la PK
   @GeneratedValue: para que sea JPA quien genere el ID de manera automatica.
   @Column(name = "nombre"): buscara o creara en la tabla de la DDBB este atributo: nombre, aun cuando en java se haya establecido private String name;

2) Definimos la relacion entre Fabricante (Maker) y Producto (Product) esta es la contra parte de la relacion uno a muchos
 - private Maker maker;
   @ManyToOne: para indicar la contraparte de muchos a uno *:1
   @JoinColumn(name = "id_fabricante"): indico bajo que id se esta generando esta relacion, null para establecer que debe estar esta relacion


*/