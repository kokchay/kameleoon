package com.kameleoon.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kameleoon.configuration.TestInitializerConfig;
import com.kameleoon.domain.dto.WeatherResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;

class WeatherControllerTest extends TestInitializerConfig {

    @Test
    void testGetWeatherInfo_WithOnDemandParam_Success() throws Exception {
        // Given
        String city = "London";
        String additionalRequestParam = "?on-demand=true";

        // When
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/weather/" + city + additionalRequestParam))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andReturn();

        // Extract response content as JSON and deserialize to WeatherResponseDto
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherResponseDto weatherResponseDto = objectMapper.readValue(
          mvcResult.getResponse().getContentAsString(),
          WeatherResponseDto.class);

        // Then
        assertThat(weatherResponseDto).isNotNull();
        assertThat(weatherResponseDto.getName()).isEqualTo(city);
        assertThat(weatherResponseDto.getWeather()).isNotNull();
        assertThat(weatherResponseDto.getWeather().getDescription()).isNotNull();
        assertThat(weatherResponseDto.getWeather().getMain()).isNotNull();
        assertThat(weatherResponseDto.getTemperature()).isNotNull();
        assertThat(weatherResponseDto.getTemperature().getTemp()).isNotNull();
    }

    @Test
    void testGetWeatherInfo_WithoutParam_Success() throws Exception {
        // Given
        String city = "London";

        // When
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/weather/" + city))
          .andExpect(MockMvcResultMatchers.status().isOk())
          .andReturn();

        // Extract response content as JSON and deserialize to WeatherResponseDto
        ObjectMapper objectMapper = new ObjectMapper();
        WeatherResponseDto weatherResponseDto = objectMapper.readValue(
          mvcResult.getResponse().getContentAsString(),
          WeatherResponseDto.class);

        // Then
        assertThat(weatherResponseDto).isNotNull();
        assertThat(weatherResponseDto.getName()).isEqualTo(city);
        assertThat(weatherResponseDto.getWeather()).isNotNull();
        assertThat(weatherResponseDto.getWeather().getDescription()).isNotNull();
        assertThat(weatherResponseDto.getWeather().getMain()).isNotNull();
        assertThat(weatherResponseDto.getTemperature()).isNotNull();
        assertThat(weatherResponseDto.getTemperature().getTemp()).isNotNull();
    }

    @Test
    void testGetWeatherInfo_CityNotFound() throws Exception {
        // Given
        String city = "NonExistentCity?on-demand=true";

        // When/Then
        mvc.perform(MockMvcRequestBuilders.get("/weather/" + city))
          .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void testGetWeatherInfo_InvalidDataSupplied() throws Exception {
        // Given
        String city = ""; // Invalid city name

        // When/Then
        mvc.perform(MockMvcRequestBuilders.get("/weather/" + city))
          .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
