package com.aluracursos.literalura.dto;

import com.aluracursos.literalura.model.Autor;
import java.util.List;
import java.util.stream.Collectors;

public record AutorDTO(String nombreAutor,
                       Integer anoNacimiento,
                       Integer anoFallecimiento,
                       List<String> titulosLibros) {

    public AutorDTO(Autor autor) {
        this(
                autor.getAutor(),
                autor.getAnoNacimiento(),
                autor.getAnoFallecimiento(),
                autor.getLibros() != null
                        ? autor.getLibros().stream()
                        .map(libro -> libro.getTitulo())
                        .collect(Collectors.toList())
                        : List.of()
        );
    }

    @Override
    public String toString() {
        return "\n----------------" +
                "Autor: " + nombreAutor +
                "\nAño Nacimiento: " + (anoNacimiento != null ? anoNacimiento : "Desconocido") +
                "\nAño Fallecimiento: " + (anoFallecimiento != null ? anoFallecimiento : "Desconocido") +
                "\nLibros: " + (titulosLibros.isEmpty() ? "Ninguno" : String.join(", ", titulosLibros)) +
                "----------------";
    }
}
