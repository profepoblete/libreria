package com.demo.libreria.controller;


import com.demo.libreria.dto.LibroRequestDTO;
import com.demo.libreria.dto.LibroResponseDTO;
import com.demo.libreria.model.Libro;
import com.demo.libreria.service.LibroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
@RequiredArgsConstructor
public class LibroController {
    private final LibroService libroService;

    @GetMapping
    public ResponseEntity<List<LibroResponseDTO>> obtenerTodos()
    {
        return ResponseEntity.ok(libroService.obtenerTodos());
    }



    @PostMapping
    public ResponseEntity<LibroResponseDTO> crear(@Valid @RequestBody LibroRequestDTO libro)
    {
        return ResponseEntity.status(201).body(libroService.guardar(libro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id)
    {
        if(libroService.obtenerPorId(id).isEmpty())
        {
            return ResponseEntity.notFound().build();
        }
        libroService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/titulo")
    public ResponseEntity<List<Libro>> buscaPorTitulo(@RequestParam String titulo)
    {
        return ResponseEntity.ok(libroService.buscaPorTitulo(titulo));
    }

    @GetMapping("/buscar/precio")
    public ResponseEntity<List<Libro>> buscarPorPrecios(@RequestParam Integer min, @RequestParam Integer max)
    {
        return ResponseEntity.ok(libroService.buscaPorPrecio(min,max));
    }

    @GetMapping("/buscar/categoria")
    public ResponseEntity<List<Libro>> buscarPorCategoriaDesc(@RequestParam String categoria)
    {
        return ResponseEntity.ok(libroService.buscaPorCategoriaDesc(categoria));
    }
}
