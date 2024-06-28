package com.bonidev.literalura.view;

import com.bonidev.literalura.dto.BookDTO;
import com.bonidev.literalura.model.PersonEntity;
import com.bonidev.literalura.service.BookApiService;
import com.bonidev.literalura.service.BookMapper;
import com.bonidev.literalura.service.BookService;
import com.bonidev.literalura.service.PersonService;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Componente principal que gestiona la interfaz de usuario y las interacciones de usuario con la aplicación.
 */
@Component
public class Principal {

    private final BookMapper mapper;
    private final BookService bookService;
    private final PersonService personService;
    private final BookApiService bookApiService;
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Constructor de la clase Principal.
     *
     * @param mapper         Instancia de BookMapper para mapear entre DTO y entidades de libros.
     * @param bookService    Servicio que gestiona operaciones relacionadas con libros.
     * @param personService  Servicio que gestiona operaciones relacionadas con personas (autores).
     * @param bookApiService Servicio que interactúa con una API externa para obtener libros.
     */
    public Principal(BookMapper mapper, BookService bookService, PersonService personService, BookApiService bookApiService) {
        this.mapper = mapper;
        this.bookService = bookService;
        this.personService = personService;
        this.bookApiService = bookApiService;
    }

    /**
     * Método principal que muestra el menú de opciones y maneja las interacciones del usuario.
     */
    public void showMenu() {
        System.out.print("\nBienvenido a la aplicación de LiterAlura!\n");

        while (true) {
            System.out.print("""
                    Seleccione una opción:
                    1. Buscar libro por su título o autor
                    2. Listar libros registrados
                    3. Listar autores registrados
                    4. Listar autores vivos en un año determinado
                    5. Listar libros por idioma
                    0. Salir de la aplicación
                    """);

            System.out.print("Opción: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    searchByTitle();
                    break;
                case 2:
                    showAllBooks();
                    break;
                case 3:
                    showAllAuthors();
                    break;
                case 4:
                    showAllLivingAuthorsInAGivenYear();
                    break;
                case 5:
                    showBooksByLanguage();
                    break;
                case 0:
                    System.out.println("\nSaliendo de la aplicación...");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
            }
        }
    }

    /**
     * Busca libros por título utilizando un servicio de API externa y los muestra al usuario.
     */
    private void searchByTitle() {
        System.out.print("\nIngrese el nombre del libro o autor: ");
        String searchTerm = scanner.nextLine().toLowerCase().replace(" ", "%20");

        List<BookDTO> bookDTOs = bookApiService.fetchBooksFromApi(searchTerm);

        if (!bookDTOs.isEmpty()) {
            System.out.println("\n" + (bookDTOs.size() == 1 ? "El libro que coincide" : "Los libros que coinciden") + " con su búsqueda:");
            System.out.println("índice. titulo [idioma(s)] <autor(es)>");
            Set<BookDTO> bookDTOSet = new HashSet<>(bookDTOs);
            List<BookDTO> bookDTOList = new ArrayList<>(bookDTOSet);

            int n = 1;
            for (BookDTO bookDTO : bookDTOList) {
                System.out.println(n + ". " + bookDTO.title() + " [" + bookDTO.getAllLanguages() + "] <" + bookDTO.getAuthorsNames() + ">");
                n++;
            }
            System.out.println("0. No me " + (bookDTOList.size() == 1 ? "interesa este libro" : "interesan estos libros"));

            int option = 1;
            while (option <= n) {
                System.out.print("\nIngrese el índice del libro para mostrar más información: ");
                option = scanner.nextInt();
                scanner.nextLine();

                if (option == 0) {
                    System.out.println();
                    return;
                }

                if (option < 1 || option > n) {
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    option = 1;
                    continue;
                }
                System.out.println(bookDTOList.get(option - 1));
                bookService.saveBook(mapper.toEntity(bookDTOList.get(option - 1)));
                break;
            }
        } else {
            System.out.println("No se encontró el libro que está buscando...\n");
        }
    }

    /**
     * Muestra todos los libros registrados en la base de datos.
     */
    private void showAllBooks() {
        List<BookDTO> books = bookService.getAllBooks()
                .stream()
                .map(mapper::toDTO)
                .toList();

        books.forEach(System.out::println);
    }

    /**
     * Muestra todos los autores registrados en la base de datos.
     */
    private void showAllAuthors() {
        List<PersonEntity> authors = personService.getAllPersons();
        authors.forEach(System.out::println);
    }

    /**
     * Muestra todos los autores que estaban vivos en un año determinado, según la base de datos.
     */
    private void showAllLivingAuthorsInAGivenYear() {
        System.out.print("\nIngrese el año que desea buscar autor(es) vivo(s): ");
        int yearRequested = scanner.nextInt();
        scanner.nextLine();
        List<PersonEntity> personsAlive = personService.getLivingPersonsInAGivenYear(yearRequested);
        personsAlive.forEach(System.out::println);
    }

    /**
     * Muestra todos los libros que están disponibles en un idioma específico, según la base de datos.
     */
    private void showBooksByLanguage() {
        System.out.println("""
                Ejemplos de códigos de idiomas:
                es -> Español
                en -> Inglés
                fr -> Francés
                pt -> Portugués
                """);
        System.out.print("Elige el código idioma del libro a buscar: ");
        String language = scanner.nextLine().toLowerCase();

        if (language.length() != 2) {
            System.out.println("El código debe tener exactamente dos caracteres...");
            return;
        }

        List<BookDTO> books = bookService.getBooksByLanguage(language)
                .stream()
                .map(mapper::toDTO)
                .toList();

        books.forEach(System.out::println);
    }
}
