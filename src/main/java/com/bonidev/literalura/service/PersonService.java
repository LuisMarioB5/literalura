package com.bonidev.literalura.service;

import com.bonidev.literalura.model.PersonEntity;
import com.bonidev.literalura.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio que gestiona operaciones relacionadas con entidades PersonEntity.
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Obtiene todas las personas almacenadas en el repositorio.
     *
     * @return Lista de todas las personas.
     */
    public List<PersonEntity> getAllPersons() {
        return personRepository.findAll();
    }

    /**
     * Obtiene todas las personas que estaban vivas en un año específico.
     *
     * @param yearRequested El año solicitado para buscar personas vivas.
     * @return Lista de personas que estaban vivas en el año especificado.
     */
    public List<PersonEntity> getLivingPersonsInAGivenYear(int yearRequested) {
        return personRepository.getLivingPersonsInAGivenYear(yearRequested);
    }
}
