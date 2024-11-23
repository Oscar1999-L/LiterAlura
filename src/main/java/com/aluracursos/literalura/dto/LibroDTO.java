package com.aluracursos.literalura.dto;

import com.aluracursos.literalura.model.Libro;

public record LibroDTO(String titulo, String autor, String idiomas, Long numeroDescargas) {
    public LibroDTO(Libro libro) {
        this(
                libro.getTitulo(),
                libro.getAutor() != null ? libro.getAutor().getAutor() : "Desconocido",
                libro.getIdiomas(),
                libro.getNumeroDescargas()
        );
    }

    @Override
    public String toString() {
        return "Libro: " + titulo +
                "\nAutor: " + autor +
                "\nIdiomas: " + idiomas +
                "\nNúmero de Descargas: " + numeroDescargas;
    }
}

