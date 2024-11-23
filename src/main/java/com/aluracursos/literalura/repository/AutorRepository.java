package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {


    List<Autor> findAll();

    List<Autor> findByAnoNacimientoLessThanOrAnoFallecimientoGreaterThanEqual(Integer anoNacimiento, Integer anoFallecimiento);


    @Query("SELECT a FROM Autor a LEFT JOIN FETCH a.libros WHERE LOWER(a.autor) LIKE LOWER(CONCAT('%', :autor, '%'))")
    Optional<Autor> findByAutorContainsIgnoreCaseWithLibros(@Param("autor") String autor);

    Optional<Autor> findFirstByAutorContainsIgnoreCase(String autor);




}
