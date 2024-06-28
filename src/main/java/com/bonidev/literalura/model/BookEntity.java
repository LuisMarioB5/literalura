package com.bonidev.literalura.model;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Entidad que representa un libro en la base de datos.
 */
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String title;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private Set<PersonEntity> authors;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "book_language",
            joinColumns = @JoinColumn(name = "book_id"))
    @Column(name = "language_code")
    private Set<String> languages = new HashSet<>();

    private String htmlLink;

    @Column(name = "download_count")
    private int downloadCount;

    /**
     * Constructor por defecto.
     */
    public BookEntity() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param title         El título del libro.
     * @param authors       Los autores del libro.
     * @param languages     Los idiomas en los que está disponible el libro.
     * @param htmlLink      El enlace HTML del libro.
     * @param downloadCount El número de descargas del libro.
     */
    public BookEntity(String title, Set<PersonEntity> authors, Set<String> languages, String htmlLink, int downloadCount) {
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.htmlLink = htmlLink;
        this.downloadCount = downloadCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<PersonEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<PersonEntity> authors) {
        this.authors = authors;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<String> languages) {
        this.languages = languages;
    }

    public String getHtmlLink() {
        return htmlLink;
    }

    public void setHtmlLink(String htmlLink) {
        this.htmlLink = htmlLink;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    /**
     * Retorna una representación en cadena de texto del objeto BookEntity.
     *
     * @return Representación en cadena de texto del objeto BookEntity.
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
               """, title, authors, languages, htmlLink, downloadCount);
    }

    /**
     * Compara este objeto BookEntity con otro para verificar si son iguales.
     *
     * @param obj El objeto a comparar.
     * @return true si los objetos son iguales, false en caso contrario.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        BookEntity book = (BookEntity) obj;
        return Objects.equals(title, book.title) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(languages, book.languages) &&
                Objects.equals(htmlLink, book.htmlLink);
    }

    /**
     * Calcula el código hash del objeto BookEntity.
     *
     * @return El código hash del objeto BookEntity.
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, authors, languages);
    }
}
