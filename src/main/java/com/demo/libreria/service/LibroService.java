package com.demo.libreria.service;


import com.demo.libreria.LibreriaApplication;
import com.demo.libreria.client.AutorClient;
import com.demo.libreria.dto.AutorDTO;
import com.demo.libreria.dto.LibroRequestDTO;
import com.demo.libreria.dto.LibroResponseDTO;
import com.demo.libreria.model.Categoria;
import com.demo.libreria.model.Libro;
import com.demo.libreria.repository.CategoriaRepository;
import com.demo.libreria.repository.LibroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LibroService {
    private final LibroRepository libroRepository;
    private final CategoriaRepository categoriaRepository;
    private final AutorClient autorClient;

    private LibroResponseDTO mapLibro(Libro libro)
    {
        AutorDTO autor = autorClient.obtenerAutor(libro.getAutorId());
        return new LibroResponseDTO(
                libro.getId()
                ,libro.getTitulo()
                ,libro.getPrecio()
                ,libro.getCategoria().getDescripcion()
                ,autor.getNombre()
        );
    }

    public List<LibroResponseDTO> obtenerTodos()
    {
        return libroRepository.findAll()
                .stream()
                .map(this::mapLibro)
                .collect(Collectors.toList());
    }

    public Optional<LibroResponseDTO> obtenerPorId(Long id)
    {
        return libroRepository.findById(id).map(this::mapLibro);
    }

    public LibroResponseDTO guardar(LibroRequestDTO libroDTO)
    {
        Categoria categoria = categoriaRepository
                .findById(libroDTO.getCategoriaId())
                .orElseThrow(()-> new RuntimeException(
                        "Categoria no existe " + libroDTO.getCategoriaId()));
        Libro libro = new Libro(null
                ,libroDTO.getTitulo()
                ,libroDTO.getPrecio()
                ,categoria
                ,libroDTO.getAutorId());
        return mapLibro(libroRepository.save(libro)) ;
    }

    public void eliminar(Long id)
    {
        libroRepository.deleteById(id);
    }

    public List<Libro> buscaPorTitulo(String titulo)
    {
        return libroRepository.findByTituloContainingIgnoreCase(titulo);
    }
    public List<Libro> buscaPorPrecio(Integer min, Integer max)
    {
        return libroRepository.findByPrecioBetween(min, max);
    }

    public List<Libro> buscaPorCategoriaDesc(String categoria)
    {
        return libroRepository.findByCategoriaDesc(categoria);
    }
}
