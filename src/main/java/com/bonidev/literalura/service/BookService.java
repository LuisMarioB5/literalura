package com.bonidev.literalura.service;

import com.bonidev.literalura.model.BookEntity;
import com.bonidev.literalura.model.PersonEntity;
import com.bonidev.literalura.repository.BookRepository;
import com.bonidev.literalura.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Servicio que gestiona la lógica de negocio relacionada con los libros.
 */
@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PersonRepository personRepository;

    /**
     * Guarda un libro en la base de datos, asegurándose de manejar autores existentes.
     *
     * @param book El libro a guardar.
     */
    @Transactional
    public void saveBook(BookEntity book) {
        // Verificar si el libro ya existe
        List<BookEntity> existingBooks = bookRepository.findAll();
        if (existingBooks.contains(book)) return;

        // Verificar si los autores ya existen y asociarlos al libro
        Set<PersonEntity> authors = new HashSet<>();
        for (PersonEntity author : book.getAuthors()) {
            PersonEntity existingAuthor = personRepository.findByNameAndBirthYearAndDeathYear(author.getName(), author.getBirthYear(), author.getDeathYear());
            if (existingAuthor != null) {
                authors.add(existingAuthor);
            }
        }

        if (!authors.isEmpty()) {
            book.setAuthors(authors);
        }

        bookRepository.save(book);
    }

    /**
     * Obtiene todos los libros almacenados en la base de datos.
     *
     * @return Lista de todos los libros.
     */
    public List<BookEntity> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Obtiene libros que están escritos en un idioma específico.
     *
     * @param language El idioma de los libros a buscar.
     * @return Lista de libros escritos en el idioma especificado.
     */
    public List<BookEntity> getBooksByLanguage(String language) {
        return bookRepository.findByLanguages(language);
    }
}
