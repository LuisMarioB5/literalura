package com.bonidev.literalura.repository;

import com.bonidev.literalura.model.PersonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Interfaz de repositorio para la entidad PersonEntity. Proporciona métodos para realizar operaciones CRUD
 * y consultas personalizadas sobre las personas.
 */
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {

    /**
     * Busca una persona por su nombre, año de nacimiento y año de fallecimiento.
     *
     * @param name      El nombre de la persona.
     * @param birthYear El año de nacimiento de la persona.
     * @param deathYear El año de fallecimiento de la persona.
     * @return La persona encontrada que coincide con los parámetros dados.
     */
    PersonEntity findByNameAndBirthYearAndDeathYear(String name, int birthYear, int deathYear);

    /**
     * Consulta todas las personas que estaban vivas en un año específico.
     *
     * @param yearRequested El año para el cual se desea obtener las personas vivas.
     * @return Una lista de personas que estaban vivas en el año especificado.
     */
    @Query(value = "SELECT p FROM PersonEntity p WHERE p.birthYear <= :year AND p.deathYear >= :year")
    List<PersonEntity> getLivingPersonsInAGivenYear(@Param("year") int yearRequested);
}
