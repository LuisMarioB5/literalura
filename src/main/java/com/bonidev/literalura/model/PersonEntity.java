package com.bonidev.literalura.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidad que representa una persona (autor) en la base de datos.
 */
@Entity
@Table(name = "persons", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "birth_year", "death_year"})})
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "birth_year")
    private int birthYear;

    @Column(name = "death_year")
    private int deathYear;

    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private Set<BookEntity> books = new HashSet<>();

    /**
     * Constructor por defecto.
     */
    public PersonEntity() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param name      El nombre de la persona.
     * @param birthYear El año de nacimiento de la persona.
     * @param deathYear El año de fallecimiento de la persona.
     */
    public PersonEntity(String name, int birthYear, int deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(int deathYear) {
        this.deathYear = deathYear;
    }

    public Set<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(Set<BookEntity> books) {
        this.books = books;
    }

    /**
     * Retorna una cadena de texto con todos los títulos de los libros escritos por la persona.
     *
     * @return Una cadena de texto con los títulos de los libros.
     */
    private String getAllBooks() {
        StringBuilder builder = new StringBuilder();

        int i = 1;
        for (BookEntity book : books) {
            builder.append(book.getTitle());
            if (books.size() != 1) {
                builder.append(books.size() == i ? ";" : "; ");
            }
            i++;
        }

        return builder.toString();
    }

    /**
     * Retorna una representación en cadena de texto del objeto PersonEntity.
     *
     * @return Representación en cadena de texto del objeto PersonEntity.
     */
    @Override
    public String toString() {
        return String.format("""
                -------------------Autor-------------------
                 Nombre: %s
                 Fecha Nacimiento: %d
                 Fecha Fallecimiento: %d
                 Libros: %s
                -------------------------------------------
                """, name, birthYear, deathYear, getAllBooks());
    }

    /**
     * Compara este objeto PersonEntity con otro para verificar si son iguales.
     *
     * @param obj El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        PersonEntity person = (PersonEntity) obj;
        return Objects.equals(name, person.name) &&
                Objects.equals(birthYear, person.birthYear) &&
                Objects.equals(deathYear, person.deathYear);
    }

    /**
     * Calcula el código hash del objeto PersonEntity.
     *
     * @return El código hash del objeto PersonEntity.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, birthYear, deathYear);
    }
}
