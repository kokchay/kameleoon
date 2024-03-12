package com.kameleoon.restexternal.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.kameleoon.domain.dto.WeatherDto;

import java.io.IOException;

/**
 * Custom deserializer for converting JSON data to WeatherDto objects.
 * This deserializer is responsible for parsing JSON data and creating WeatherDto instances.
 */
public class WeatherDtoDeserializer extends StdDeserializer<WeatherDto> {

    public WeatherDtoDeserializer() {
        this(null);
    }

    public WeatherDtoDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Deserialize JSON data into a WeatherDto object.
     *
     * @param jp    JsonParser for reading JSON data.
     * @param ctxt  DeserializationContext for deserialization operations.
     * @return WeatherDto object created from the JSON data.
     * @throws IOException If there is an error reading the JSON data.
     */
    @Override
    public WeatherDto deserialize(JsonParser jp, DeserializationContext ctxt)
      throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        if (node.isArray() && node.size() == 1) {
            node = node.get(0);
        }
        return new WeatherDto(
          node.get("main").asText(),
          node.get("description").asText()
        );
    }
}
