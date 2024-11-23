package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LiterRepository extends JpaRepository<Libro, Long> {

    boolean existsByTitulo(String titulo);

    List<Libro> findByTituloContainsIgnoreCase(String titulo);

    List<Libro> findByIdiomas(String idiomas);

    @Query("SELECT s FROM Libro s ORDER BY s.numeroDescargas DESC LIMIT 10")
    List<Libro> findByTop10ByTituloBynumeroDescargas();

}
