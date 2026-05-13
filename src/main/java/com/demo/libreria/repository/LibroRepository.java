package com.demo.libreria.repository;

import com.demo.libreria.model.Libro;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {

    List<Libro> findByTituloContainingIgnoreCase(String titulo);

    List<Libro> findByPrecioBetween(Integer min, Integer max);

    @Query(
            value = "SELECT l.* FROM libros l inner join categorias c on l.categoria_id=c.id where c.descripcion = :categoria_desc"
            ,nativeQuery = true
    )
    List<Libro> findByCategoriaDesc(@Param("categoria_desc") String categoria_desc);

}
