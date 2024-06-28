package com.bonidev.literalura.service;

import com.bonidev.literalura.dto.BookDTO;
import com.bonidev.literalura.model.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

/**
 * Servicio que realiza la conversión entre entidades BookEntity y DTO BookDTO.
 */
@Service
public class BookMapper {

    @Autowired
    private PersonMapper personMapper;

    /**
     * Convierte una entidad BookEntity a un DTO BookDTO.
     *
     * @param bookEntity La entidad BookEntity a convertir.
     * @return El DTO BookDTO correspondiente.
     */
    public BookDTO toDTO(BookEntity bookEntity) {
        return new BookDTO(
                bookEntity.getTitle(),
                bookEntity.getAuthors().stream()
                        .map(personMapper::toDTO)
                        .collect(Collectors.toSet()),
                bookEntity.getLanguages(),
                bookEntity.getHtmlLink(),
                bookEntity.getDownloadCount()
        );
    }

    /**
     * Convierte un DTO BookDTO a una entidad BookEntity.
     *
     * @param bookDTO El DTO BookDTO a convertir.
     * @return La entidad BookEntity correspondiente.
     */
    public BookEntity toEntity(BookDTO bookDTO) {
        return new BookEntity(
                bookDTO.title(),
                bookDTO.authors().stream()
                        .map(personMapper::toEntity)
                        .collect(Collectors.toSet()),
                bookDTO.languages(),
                bookDTO.htmlLink(),
                bookDTO.downloadCount()
        );
    }
}
