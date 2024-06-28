package com.bonidev.literalura.dto;

import com.bonidev.literalura.service.NestedJsonDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Clase que representa un Data Transfer Object (DTO) para un libro.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record BookDTO(
        // Título del libro.
        String title,

        // Conjunto de autores del libro.
        Set<PersonDTO> authors,

        // Conjunto de idiomas en los que está disponible el libro.
        Set<String> languages,

        // Enlace HTML del libro.
        @JsonProperty("formats")
        @JsonDeserialize(using = NestedJsonDeserializer.class)
        String htmlLink,

        // Número de descargas del libro.
        @JsonProperty("download_count")
        int downloadCount
) {
    /**
     * Obtiene los nombres de los autores del libro en una cadena de texto.
     *
     * @return Nombres de los autores en una cadena de texto separados por punto y coma.
     */
    public String getAuthorsNames() {
        StringBuilder builder = new StringBuilder();

        int i = 1;
        for (PersonDTO author : this.authors) {
            builder.append(author.name());
            if (this.authors.size() != 1) {
                builder.append(this.authors.size() == i ? ";" : "; ");
            }
            i++;
        }

        return builder.toString();
    }

    /**
     * Obtiene todos los idiomas del libro en una cadena de texto.
     *
     * @return Idiomas del libro en una cadena de texto separados por punto y coma.
     */
    public String getAllLanguages() {
        StringBuilder builder = new StringBuilder();
        List<String> languagesList = List.copyOf(languages);

        int i = 0;
        for (String ignored : this.languages) {
            builder.append(languagesList.get(i));
            if (this.languages.size() != 1) {
                builder.append(this.languages.size() == i + 1 ? ";" : "; ");
            }
            i++;
        }

        return builder.toString();
    }

    /**
     * Retorna una representación en cadena de texto del objeto BookDTO.
     *
     * @return Representación en cadena de texto del objeto BookDTO.
     */
    @Override
    public String toString() {
        return String.format("""
                ------------------LIBRO------------------
                 Título: %s
                 Autor: %s
                 Idioma: %s
                 Link HTML: %s
                 Número de descargas: %d
                -----------------------------------------
                """, title, getAuthorsNames(), getAllLanguages(), htmlLink, downloadCount);
    }

    /**
     * Compara este objeto BookDTO con otro para verificar si son iguales.
     *
     * @param obj El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BookDTO book = (BookDTO) obj;
        return Objects.equals(title, book.title) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(languages, book.languages);
    }

    /**
     * Calcula el código hash del objeto BookDTO.
     *
     * @return El código hash del objeto BookDTO.
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, authors, languages);
    }
}
