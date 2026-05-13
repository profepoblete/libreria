package com.demo.libreria.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroResponseDTO {
    private Long id;
    private String titulo;
    private Integer precio;
    private String categoriaNombre;
    private String autorNombre;
}
