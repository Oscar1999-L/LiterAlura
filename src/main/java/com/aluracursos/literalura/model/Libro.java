package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String titulo;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "autor_id")
    private Autor autor;
    private String idiomas;
    private Long numeroDescargas;

    public Libro() {}

    public Libro(DatosLibro datosLibro) {
        this.Id = datosLibro.id();
        this.titulo = datosLibro.titulo();
        if (datosLibro.autores() != null && !datosLibro.autores().isEmpty()) {
            this.autor = new Autor(datosLibro.autores().get(0));
        } else {
            this.autor = null;
        }
        this.idiomas = ListaIdiomas(datosLibro.idiomas());
        this.numeroDescargas = datosLibro.numeroDescargas();
    }

    private String ListaIdiomas(List<String> idiomas) {
        return (idiomas == null || idiomas.isEmpty()) ? "Desconocido" : idiomas.get(0);
    }

    @Override
    public String toString() {
        return "\n----------------" +
                "Libro" +
                "\nTitulo: " + titulo +
                "\nAutor: " + (autor != null ? autor.getAutor() : "Desconocido") +
                "\nIdiomas: " + idiomas +
                "\nNumero Descargas: " + numeroDescargas
                + "----------------";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Long getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Long numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }
}
