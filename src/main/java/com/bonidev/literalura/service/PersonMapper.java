package com.bonidev.literalura.service;

import com.bonidev.literalura.dto.PersonDTO;
import com.bonidev.literalura.model.PersonEntity;
import org.springframework.stereotype.Service;

/**
 * Clase que proporciona métodos para mapear entre PersonEntity y PersonDTO.
 */
@Service
public class PersonMapper {

    /**
     * Convierte una entidad PersonEntity a un DTO PersonDTO.
     *
     * @param personEntity La entidad PersonEntity a convertir.
     * @return El DTO PersonDTO creado a partir de la entidad.
     */
    public PersonDTO toDTO(PersonEntity personEntity) {
        return new PersonDTO(
                personEntity.getName(),
                personEntity.getBirthYear(),
                personEntity.getDeathYear()
        );
    }

    /**
     * Convierte un DTO PersonDTO a una entidad PersonEntity.
     *
     * @param personDTO El DTO PersonDTO a convertir.
     * @return La entidad PersonEntity creada a partir del DTO.
     */
    public PersonEntity toEntity(PersonDTO personDTO){
        return new PersonEntity(
                personDTO.name(),
                personDTO.birthYear(),
                personDTO.deathYear()
        );
    }
}
