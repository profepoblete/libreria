package com.demo.libreria.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibroRequestDTO {

    @NotBlank(message = "El titulo es obligatorio")
    private String titulo;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a cero")
    private Integer precio;

    @NotNull(message = "El id categoría es obligatorio")
    private Long categoriaId;

    @NotNull(message = "El id de autor es obligaotrio")
    private Long autorId;
}
