package com.kameleoon.restexternal.configuraiton;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.kameleoon.domain.dto.WeatherDto;
import com.kameleoon.restexternal.deserializer.WeatherDtoDeserializer;
import com.kameleoon.restexternal.handler.RestErrorHandler;
import com.kameleoon.restexternal.interceptor.RequestResponseInterceptor;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class RestClientConfig {

  SimpleModule module;

  /**
   * Configures and initializes a RestTemplate bean for making HTTP requests.
   *
   * @return RestTemplate configured with interceptors, error handlers, and message converters.
   */
  @Bean
  public RestTemplate restTemplate() {
    RestTemplate restTemplate = new RestTemplate(
      new BufferingClientHttpRequestFactory(
        new SimpleClientHttpRequestFactory()
      )
    );

    List<ClientHttpRequestInterceptor> interceptors
      = restTemplate.getInterceptors();
    if (CollectionUtils.isEmpty(interceptors)) {
      interceptors = new ArrayList<>();
    }
    interceptors.add(new RequestResponseInterceptor(objectMapper()));
    restTemplate.setInterceptors(interceptors);
    restTemplate.setErrorHandler(new RestErrorHandler());
    restTemplate.setMessageConverters(List.of(customJackson2HttpMessageConverter()));
    return restTemplate;
  }

  /**
   * Configures and initializes an ObjectMapper bean for JSON serialization and deserialization.
   *
   * @return ObjectMapper configured with custom modules and features.
   */
  @Bean
  public ObjectMapper objectMapper() {
    final ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    objectMapper.findAndRegisterModules();
    return objectMapper;
  }

  /**
   * Configures and initializes a MappingJackson2HttpMessageConverter bean for JSON message conversion.
   *
   * @return MappingJackson2HttpMessageConverter configured with a custom ObjectMapper.
   */
  @Bean
  public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
    final MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
    jsonConverter.setObjectMapper(objectMapper());
    module.addDeserializer(WeatherDto.class, new WeatherDtoDeserializer());
    objectMapper().registerModule(module);

    return jsonConverter;
  }
}
