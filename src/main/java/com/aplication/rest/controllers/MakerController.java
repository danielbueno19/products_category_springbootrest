package com.aplication.rest.controllers;

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

import com.aplication.rest.controllers.dto.MakerDTO;
import com.aplication.rest.entities.Maker;
import com.aplication.rest.service.IMakerService;

import java.net.URISyntaxException;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/maker")
public class MakerController {
    @Autowired
    private IMakerService iMakerService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Maker> makerOptional = iMakerService.findById(id);

        if(makerOptional.isPresent()){
            Maker maker = makerOptional.get();

            MakerDTO makerDTO = MakerDTO.builder()
                                        .id(maker.getId())
                                        .name(maker.getName())
                                        .productList(maker.getProductList())
                                        .build();
            return ResponseEntity.ok(makerDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll(){
        List<MakerDTO> makerList = iMakerService.findAll()
                                                .stream()
                                                .map(maker -> MakerDTO.builder()
                                                                        .id(maker.getId())
                                                                        .name(maker.getName())
                                                                        .productList(maker.getProductList())
                                                                        .build())
                                                .collect(Collectors.toList());
        
        return ResponseEntity.ok(makerList);        
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MakerDTO makerDTO) throws URISyntaxException{
        if(makerDTO.getName().isBlank()){
            return ResponseEntity.badRequest().build();
        }
        iMakerService.save(Maker.builder().name(makerDTO.getName()).build());

        return ResponseEntity.created(new URI("/api/maker/save")).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMaker(@PathVariable Long id, @RequestBody MakerDTO makerDTO){
        Optional<Maker> makerOptional = iMakerService.findById(id);

        if(makerOptional.isPresent()){
            Maker maker = makerOptional.get();
            maker.setName(makerDTO.getName());
            iMakerService.save(maker);

            return ResponseEntity.ok("Registro Actualizado Correctamente");
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBYId(@PathVariable Long id){
        
        Optional<Maker> makerOptional = iMakerService.findById(id); 

        if(makerOptional.isPresent()){
            iMakerService.deleteById(id);

            return ResponseEntity.ok("Registro eliminado correctamente");
        }

        return ResponseEntity.badRequest().build();
    }
}

/*
1) las clases controller es un componente que se encarga de recibir, procesar y responder las peticiones HTTP que llegan a nuestra aplicación
   y tendra las siguientes anotaciones:
   @RestController: devuelven directamente el objeto de dominio serializado en formato JSON o XML, en lugar de una vista.
   @RequestMapping: Con esta anotación, puedes especificar la URL, el método HTTP entreo otros...

 - Inyectamos el service, con su respectivo @Autowired

 - ResponseEntity<?>:  maneja toda la respuesta HTTP incluyendo el cuerpo, cabecera y códigos de estado permitiéndonos total libertad
   de configurar la respuesta que queremos que se  envié desde nuestros endpoints. y deacuerdo a la que queramos que haga llevara alguna de estas anotaciones:
   @GetMapping: para obtener información de la BD
   @PostMapping: Para agregar o modificar información
   @DeleteMapping: para eliminar información. 

   @PathVariable: se usa para obtener datos de la ruta de la solicitud, que en postman para pruebas es ingresado manualmente

 - Retornar una respuesta con el Entity es una mala practica, asi que se acude a un patron de diseño DTO (Data Transfer Object)
 - @RequestBody: se usa para obtener datos del cuerpo de la solicitud.

*/