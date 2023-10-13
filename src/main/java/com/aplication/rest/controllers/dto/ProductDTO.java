package com.aplication.rest.controllers.dto;

import java.math.BigDecimal;
import com.aplication.rest.entities.Maker;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private BigDecimal price;

    private Maker maker;
}
