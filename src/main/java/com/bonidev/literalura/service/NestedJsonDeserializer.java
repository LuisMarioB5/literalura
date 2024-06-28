package com.bonidev.literalura.service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

/**
 * Deserializador personalizado para extraer datos anidados de JSON.
 */
public class NestedJsonDeserializer extends StdDeserializer<String> {

    protected NestedJsonDeserializer() {
        super((Class<?>) null);
    }

    protected NestedJsonDeserializer(Class<?> vc) {
        super(vc);
    }

    protected NestedJsonDeserializer(JavaType valueType) {
        super(valueType);
    }

    protected NestedJsonDeserializer(StdDeserializer<?> src) {
        super(src);
    }

    /**
     * Método para deserializar un campo anidado específico de un JSON.
     *
     * @param jp Objeto JsonParser para leer el JSON.
     * @param dc Objeto DeserializationContext para el contexto de deserialización.
     * @return El valor del campo "text/html" si existe en el JSON, de lo contrario devuelve "hola".
     * @throws IOException Sí ocurre un error de E/S durante la deserialización.
     */
    @Override
    public String deserialize(JsonParser jp, DeserializationContext dc) throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        JsonNode htmlNode = node.get("text/html");
        return htmlNode != null ? htmlNode.asText() : "hola";
    }
}
