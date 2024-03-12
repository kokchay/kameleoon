package com.kameleoon.restexternal.configuraiton;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kameleoon.domain.dto.WeatherDto;
import com.kameleoon.restexternal.deserializer.WeatherDtoDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class JacksonConfiguration {

    @Bean
    @Primary
    public SimpleModule customDeserializerModule() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(WeatherDto.class, new WeatherDtoDeserializer());
        return module;
    }
}
