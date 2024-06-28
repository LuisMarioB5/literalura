package com.bonidev.literalura.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Clase que representa un Data Transfer Object (DTO) para una persona.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record PersonDTO(
        // Nombre de la persona.
        String name,

        // Año de nacimiento de la persona.
        @JsonProperty("birth_year")
        int birthYear,

        // Año de fallecimiento de la persona.
        @JsonProperty("death_year")
        int deathYear) {

    /**
     * Retorna una representación en cadena de texto del objeto PersonDTO.
     *
     * @return Representación en cadena de texto del objeto PersonDTO.
     */
    @Override
    public String toString() {
        return String.format("""
               ------------------Persona------------------
                Nombre: %s
                Fecha Nacimiento: %d
                Fecha Fallecimiento: %d
               -------------------------------------------
               """, name, birthYear, deathYear);
    }
}
