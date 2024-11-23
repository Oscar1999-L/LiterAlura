package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.dto.AutorDTO;
import com.aluracursos.literalura.dto.LibroDTO;
import com.aluracursos.literalura.model.Autor;
import com.aluracursos.literalura.model.DatosLibro;
import com.aluracursos.literalura.model.Libreria;
import com.aluracursos.literalura.model.Libro;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LiterRepository;
import com.aluracursos.literalura.service.ConvertirDatos;
import com.aluracursos.literalura.service.consultaAPI;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import java.util.*;

public class Principal {
    private Scanner scanner = new Scanner(System.in);
    private consultaAPI consultaAPI = new consultaAPI();
    private ConvertirDatos convertirDatos = new ConvertirDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";

    private List<Libro> libros = new ArrayList<>();
    private LiterRepository literRepository;
    List<Autor> autors = new ArrayList<>();
    @Autowired
    private AutorRepository autorRepository;
    public Principal(LiterRepository literRepository, AutorRepository autorRepository){
        this.literRepository = literRepository;
        this.autorRepository = autorRepository;
    }

    public void muestraElMenu(){
        var opc = -1;
        while (opc != 0) {
            System.out.println();
            var menu = """
                    1 - Buscar Libro
                    2 - Lista de Libros
                    3 - Autores
                    4 - Autores vivos en determinado año
                    5 - Libros por Idioma
                    0 - Salir
                    """;

            try {
                System.out.println(menu);
                opc = scanner.nextInt();
                scanner.nextLine();
            }catch(InputMismatchException e){
                System.out.println("\n---Ingrese una opción válida---");
                scanner.nextLine();
                continue;
            }

            switch (opc) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    listaLibros();
                    break;
                case 3:
                    buscarAutores();
                    break;
                case 4:
                    fechasAutores();
                    break;
                case 5:
                    librosIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }



    private Libro conexionApi(){
        System.out.print("Escribe el título a buscar: ");
        var titulo = scanner.nextLine();
        var resultado = consultaAPI.obtenerDatos(URL_BASE + titulo.replace(" ", "%20"));

        Libreria informacion = convertirDatos.obtenerDatos(resultado,Libreria.class);

        if (informacion != null && informacion.getResultadoLibros() != null && !informacion.getResultadoLibros().isEmpty()) {
            DatosLibro primerLibro = informacion.getResultadoLibros().get(0);
            return new Libro(primerLibro);
        } else {
            System.out.println("No se encontraron resultados.");
            return null;
        }

    }

    private void buscarLibro(){
        Libro conexion = conexionApi();


        if (conexion == null){
            System.out.println("Libro no encontrado, intente otra opción...");
            return;
        }

        try {
            boolean libroExists = literRepository.existsByTitulo(conexion.getTitulo());
            if(libroExists){
                System.out.println("Libro ya registrado");
            }else {
                literRepository.save(conexion);
                System.out.println(conexion.toString());
            }
        }catch (InvalidDataAccessApiUsageException e){
            System.out.println(e);
        }
    }

    private void listaLibros() {
        libros = literRepository.findAll(); // Llenar la lista con datos de la BD.

        System.out.print("Escribe el título del libro:");
        var nombreLibro = scanner.nextLine();

        Optional<Libro> libro = libros.stream()
                .filter(s -> s.getTitulo().toLowerCase().contains(nombreLibro.toLowerCase()))
                .findFirst();

        if (libro.isPresent()) {
            LibroDTO libroDTO = new LibroDTO(libro.get());
            System.out.println(libroDTO);
        } else {
            System.out.println("Libro no encontrado...");
        }
    }

    @Transactional
    private void buscarAutores() {
        listaAutores();

        autors = autorRepository.findAll();

        System.out.print("\nEscribe el nombre del autor que deseas buscar: ");
        var nombreAutor = scanner.nextLine();

        Optional<Autor> autor = autorRepository.findByAutorContainsIgnoreCaseWithLibros(nombreAutor);

        if (autor.isPresent()) {
            AutorDTO autorDTO = new AutorDTO(autor.get());
            System.out.println("\n" + autorDTO);
        } else {
            System.out.println("\nAutor no encontrado...");
        }


    }

    private void listaAutores() {

        List<Autor> autores = autorRepository.findAll();

        if(autores.isEmpty()){
            System.out.println("No hay regisgtros en la base de datos...");
        }else{
            Set<String> autoresUnicos = new HashSet<>();
            for (Autor autor : autores){
                if (autoresUnicos.add(autor.getAutor())){
                    System.out.println(autor.getAutor());
                }
            }
        }
    }

    private void fechasAutores() {
        System.out.print("Escribe el año para consultar que autores estan vivos: ");
        var anioBuscado = scanner.nextInt();
        scanner.nextLine();

        List<Autor> autoresVivos = autorRepository.findByAnoNacimientoLessThanOrAnoFallecimientoGreaterThanEqual(anioBuscado, anioBuscado);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores que estuvieran vivos en el año " + anioBuscado + ".");
        } else {
            System.out.println("Autores vivos en " + anioBuscado + ":");
            Set<String> autoresUnicos = new HashSet<>();

            for (Autor autor : autoresVivos) {
                if (autor.getAnoNacimiento() != null && autor.getAnoFallecimiento() != null) {
                    if (autor.getAnoNacimiento() <= anioBuscado && autor.getAnoFallecimiento() >= anioBuscado) {
                        if (autoresUnicos.add(autor.getAutor())) {
                            System.out.println("Autor: " + autor.getAutor());
                        }
                    }
                }
            }
        }
    }

    private void librosIdioma() {
        var menu = """
                Escribe una de las siguientes opciones para buscar por idioma
                es -> Español
                en -> Inglés
                """;

        System.out.println(menu);
        var idioma = scanner.nextLine();
        List<Libro> librosPorIdioma = literRepository.findByIdiomas(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No hay registros en la base de datos.");
        } else {
            System.out.println(
                    idioma.equalsIgnoreCase("en") ? "Inglés" :
                    idioma.equalsIgnoreCase("es") ? "Español" :
                            "Idioma no reconocido");
            for (Libro libro : librosPorIdioma) {
                System.out.println(libro.toString());
            }
        }
    }

}
