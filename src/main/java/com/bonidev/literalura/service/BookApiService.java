package com.bonidev.literalura.service;

import com.bonidev.literalura.dto.BookDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Servicio para interactuar con una API de libros externa.
 */
@Service
public class BookApiService {

    private static final String API_URL = "https://gutendex.com/books/";

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    /**
     * Constructor que inicializa el cliente HTTP y el ObjectMapper.
     */
    public BookApiService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    /**
     * Método para obtener libros desde la API basado en un término de búsqueda.
     *
     * @param searchTerm El término de búsqueda para buscar libros en la API.
     * @return Una lista de objetos BookDTO que representan los libros encontrados.
     * @throws RuntimeException Sí ocurre un error durante la solicitud HTTP o el procesamiento JSON.
     */
    public List<BookDTO> fetchBooksFromApi(String searchTerm) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + "?search=" + searchTerm))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return deserializeBooks(response.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al obtener libros de la API", e);
        }
    }

    /**
     * Método privado para deserializar la respuesta JSON de la API en una lista de objetos BookDTO.
     *
     * @param responseBody El cuerpo de la respuesta JSON de la API.
     * @return Una lista de objetos BookDTO deserializados desde la respuesta JSON.
     * @throws RuntimeException Sí hay un error al procesar la respuesta JSON.
     */
    private List<BookDTO> deserializeBooks(String responseBody) {
        try {
            JsonNode rootNode = objectMapper.readTree(responseBody);

            if (rootNode.has("results")) {
                JsonNode resultsNode = rootNode.get("results");
                BookDTO[] books = objectMapper.convertValue(resultsNode, BookDTO[].class);
                return Arrays.asList(books);
            } else {
                throw new RuntimeException("El JSON de respuesta no contiene el campo 'results'");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error al procesar la respuesta JSON", e);
        }
    }
}
