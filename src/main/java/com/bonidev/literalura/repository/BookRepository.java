package com.bonidev.literalura.repository;

import com.bonidev.literalura.model.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Interfaz de repositorio para la entidad BookEntity. Proporciona métodos para realizar operaciones CRUD
 * y consultas personalizadas sobre los libros.
 */
public interface BookRepository extends JpaRepository<BookEntity, Long> {

    /**
     * Consulta los libros que están escritos en un idioma específico.
     *
     * @param language El código del idioma por el cual se quiere buscar.
     * @return Una lista de libros que están escritos en el idioma especificado.
     */
    List<BookEntity> findByLanguages(String language);
}
