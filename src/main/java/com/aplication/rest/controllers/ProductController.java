package com.aplication.rest.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aplication.rest.controllers.dto.ProductDTO;
import com.aplication.rest.entities.Product;
import com.aplication.rest.service.IProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Product> productOptional = iProductService.findById(id);

        if(productOptional.isPresent()){
            Product product = productOptional.get();

            ProductDTO productDTO = ProductDTO.builder()
                                                .id(product.getId())
                                                .name(product.getName())
                                                .price(product.getPrice())
                                                .maker(product.getMaker())
                                                .build();
            return ResponseEntity.ok(productDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<ProductDTO> productList = iProductService.findAll()
                                                      .stream()
                                                      .map(product -> ProductDTO.builder()
                                                                                 .id(product.getId())
                                                                                 .name(product.getName())
                                                                                 .price(product.getPrice())
                                                                                 .maker(product.getMaker())
                                                                                 .build())
                                                      .collect(Collectors.toList());

        return ResponseEntity.ok(productList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductDTO productDTO) throws URISyntaxException{
        if(productDTO.getName().isBlank() || productDTO.getPrice() == null || productDTO.getMaker() == null){
            return ResponseEntity.badRequest().build();
        }
        Product product = Product.builder()
                                 .name(productDTO.getName())
                                 .price(productDTO.getPrice())
                                 .maker(productDTO.getMaker())
                                 .build();

        iProductService.save(product);

        return ResponseEntity.created(new URI("/api/product/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO){
        Optional<Product> productOptional = iProductService.findById(id);

        if(productOptional.isPresent()){
            Product product = productOptional.get();

            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setMaker(productDTO.getMaker());

            iProductService.save(product);

            return ResponseEntity.ok("Registro Actualizado Correctamente");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Product> productOptional = iProductService.findById(id);

        if(productOptional.isPresent()){
            iProductService.deleteById(id);

            return ResponseEntity.ok("Registro eliminado exitosamente");
        }

        return ResponseEntity.notFound().build();
    }
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
   @JsonIgnore: indica que no lo serialize si es que no se lo he indicado


*/