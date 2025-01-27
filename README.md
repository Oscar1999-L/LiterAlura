## LiterAlura, biblioteca virtual powered by Project Gutenberg

Este programa es una aplicación que permite gestionar autores y libros, proporcionando funcionalidades para buscar información, listar datos y manejar relaciones entre autores y sus obras. 

## Funcionalidades Principales

### 1. **Gestión de Autores**
- **Buscar Autor por Nombre**:
  - Permite buscar un autor por su nombre (sin importar mayúsculas o minúsculas).
  - Muestra los datos del autor, como:
    - Nombre del autor.
    - Año de nacimiento y fallecimiento (si aplica).
    - Lista de libros asociados al autor.
- **Listar Todos los Autores**:
  - Muestra una lista completa de todos los autores registrados en la base de datos.

### 2. **Gestión de Libros**
- **Buscar Libro por Título**:
  - Busca libros cuyo título contenga una cadena específica.
  - Retorna información como:
    - Título del libro.
    - Idioma.
    - Autor asociado al libro.


### 4. **Conexión a Base de Datos**
- Utiliza `Spring Data JPA` para gestionar datos.
- Soporta operaciones de consulta como:
  - Buscar autores por nombre.
  - Listar libros o autores.
  - Realiza busquedas basadas en el idioma del libro o si un autor estaba vivo en determinado año.

### 5. **Modelos y DTOs**
- **Modelo de Autor**:
  - Incluye propiedades como nombre, año de nacimiento, año de fallecimiento y la lista de libros asociados.
- **Modelo de Libro**:
  - Incluye propiedades como título, idioma y autor.
- **AutorDTO**:
  - Proporciona una representación legible del autor y sus libros.

## Tecnologías Utilizadas
- **Lenguaje**: Java 17+
- **Frameworks**:
  - Spring Boot
  - Spring Data JPA
- **Base de Datos**: PostgresSQL v8.12
- **Dependencias**:
  - Hibernate
  - Lombok (opcional)
- **Herramientas de Construcción**: Maven

## Requisitos del Sistema
- Java 17 o superior.
- Maven 3.x.
- IDE como IntelliJ IDEA, Eclipse o cualquier editor compatible con Java.

## Instalación y Configuración

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/nombre-del-repositorio.git

2.- Modificar las VARIABLES DE SISTEMA en application.properties
   Primero, define las variables de sistema en tu entorno operativo. Aquí se detalla cómo hacerlo en diferentes sistemas operativos:
    
    En Windows:
    
      - Abre el menú Inicio y busca "Variables de entorno".
      - Haz clic en "Editar las variables de entorno del sistema".
      - En la sección "Variables del sistema", haz clic en "Nueva" o selecciona una existente y edítala.
      - Ingresa el nombre y el valor de la variable, por ejemplo:
        -  Nombre de la variable: DB_NAME
        -  Valor de la variable: jdbc:mysql://localhost:3306/mi_base_de_datos
          -DB_NAME = Nombre de la Base de Datos
          -DB_HOST = localhost
          -DB_USER = Nombre de usuario
          -DB_PASSWORD = Contraseña
  ## Uso del Programa
    1. Ejecuta el programa y sigue las instrucciones del menú principal.
    
    2. Usa las opciones para buscar autores, libros o listar datos.
    
    3. Observa los resultados directamente en la consola.
  ## Posibles Mejoras Futuras
    Implementar una interfaz gráfica para mejorar la experiencia del usuario.
    Añadir opciones para busquedas mejoradas como un 'Top 10 libros mas descargados'
