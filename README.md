# LiterAlura - Challenge ONE: Proyecto Finalizado

¡Bienvenido/a al repositorio del proyecto LiterAlura en Spring Boot! Este proyecto es la consolidación de mis conocimientos en
los tres primeros curso de la formación **Java y Spring Boot G6 - ONE**, ofrecido por [Alura LATAM](https://www.aluracursos.com) en colaboración con [Oracle](https://www.oracle.com/lad/) en
el programa [Oracle Next Education](https://www.oracle.com/lad/education/oracle-next-education). Aquí encontrarás todos los recursos y el código fuente necesarios para 
implementar y comprender el funcionamiento de este desafío enfocado en el manejo de datos literarios y autores. 

## Acerca del Proyecto

El proyecto LiterAlura es una aplicación desarrollada en Spring Boot que permite gestionar libros y autores mediante el consumo de la API [Gutendex](https://gutendex.com/), manteniendo una base de datos de registros literarios. Utiliza PostgresSQL como base de datos e Hibernate para la capa de persistencia. La lógica del proyecto se realizó siguiendo las especificaciones propuestas por Alura LATAM en el archivo de [Trello](https://trello.com/b/WDyMPDMb/literalura-challenge-java)

### Funcionalidades Principales

- **Gestión de Libros**: Permite buscar libros por título, listar todos los libros registrados y filtrar libros por idioma.
- **Gestión de Autores**: Permite buscar libros por autor, listar todos los autores registrados y encontrar autores que estaban vivos en un año determinado.
- **Integración con API Externa**: Incluye la capacidad de buscar libros usando la API [Gutendex](https://gutendex.com/) y guardar los resultados en la base de datos local.

### Requisitos para ejecutar el proyecto

- **Java 17**: Asegúrate de tener instalado Java 17 o una versión posterior.
- **PostgreSQL**: Debes tener acceso a una instancia de PostgreSQL. Configura las credenciales y la URL de la base de datos en `application.properties`.
- **Maven**: Necesitarás Maven para gestionar las dependencias y compilar el proyecto.

### Instalación

1. **Clona el repositorio:**

    ```shell
    git clone https://github.com/LuisMarioB5/literalura.git
    cd literalura
    ```

2. **Configuración de la base de datos:**
    - Asegúrate de tener PostgreSQL instalado y configurado.
    - Configura las credenciales y la URL de la base de datos en `application.properties`.

3. **Compilar y ejecutar el proyecto:**

    ```shell
    mvn clean install
    mvn spring-boot:run
    ```

### Uso

Una vez ejecutado, la aplicación desplegará un menú interactivo donde podrás:
- Buscar libros por títulos o autores.
- Listar todos los libros y autores anteriormente buscados.
- Filtrar libros por idioma.
- Encontrar autores vivos en un año específico.

### Estructura del proyecto

```text
src/main/
|-- java/com/bonidev/literalura/
|   |-- dto/
|   |   |-- BookDTO.java
|   |   |-- PersonDTO.java
|   |-- model/
|   |   |-- BookEntity.java
|   |   |-- PersonEntity.java
|   |-- repository/
|   |   |-- BookRepository.java
|   |   |-- PersonRepository.java
|   |-- service/
|   |   |-- BookApiService.java
|   |   |-- BookMapper.java
|   |   |-- BookService.java
|   |   |-- NestedJsonDeserializer.java
|   |   |-- PersonMapper.java
|   |   |-- PersonService.java
|   |-- view/
|   |   |-- Principal.java
|   |-- LiteraluraApplication.java
|-- resources/
    |-- application.properties
```

### Tecnologías Implementadas

- [**Spring Boot**](https://docs.spring.io/spring-boot/index.html): Framework para la creación de aplicaciones Java basadas en Spring.
- [**Hibernate**](https://hibernate.org/orm/documentation/6.5/): ORM para mapeo objeto-relacional.
- [**PostgreSQL**](https://www.postgresql.org/docs/): Sistema de gestión de bases de datos relacional.
- [**Maven**](https://maven.apache.org/guides/): Herramienta de gestión de proyectos y dependencias.

## Agradecimientos y Recursos

Agradezco a [Alura LATAM](https://www.aluracursos.com) por la formación y los recursos proporcionados, que han sido fundamentales para el desarrollo de este proyecto. A continuación, algunos de los recursos que fueron de gran ayuda para realizar el proyecto:

1. **Cursos de Alura LATAM:**
    - [Java: Trabajando con lambdas, streams y Spring Framework](https://app.aluracursos.com/course/java-trabajando-lambdas-streams-spring-framework)
    - [Java: Persistencia de datos y consultas con Spring Data JPA](https://app.aluracursos.com/course/java-persistencia-datos-consultas-spring-data-jpa)
    - [Java: Creando tu primera API y conectándola al FrontEnd](https://app.aluracursos.com/course/java-api-conectandola-front-end)
    
2. **Archivos proporcionados por Alura LATAM:**
    - [Cards en Trello](https://trello.com/b/WDyMPDMb/literalura-challenge-java)

3. **Links Externos**
    - [Gutendex](https://gutendex.com/)
    - [Spring Data JPA](https://docs.spring.io/spring-data/jpa/reference/index.html)
    - [JPA Query Methods](https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html)

## Licencia

Este proyecto se distribuye bajo la [Licencia MIT](LICENSE.md), permitiendo su uso, modificación y distribución libre.