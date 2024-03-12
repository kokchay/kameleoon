
package com.kameleoon.domain.service;

import com.kameleoon.domain.adapter.RestExternalService;
import com.kameleoon.domain.adapter.WeatherCacheService;
import com.kameleoon.domain.common.ErrorCodes;
import com.kameleoon.domain.configuraiton.WeatherConfigurationProperties;
import com.kameleoon.domain.dto.WeatherResponseDto;
import com.kameleoon.domain.exception.ExternalServiceResponseException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

/**
 * Service class for retrieving weather information.
 * This class facilitates the retrieval of weather data either from a cache or an external source based on the provided parameters.
 */
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class WeatherServiceImpl implements WeatherService {

  WeatherCacheService weatherCacheService;
  WeatherConfigurationProperties props;
  RestExternalService restExternalService;


  /**
   * Retrieves weather information for the specified city.
   *
   * @param cityName   The name of the city for which weather information is requested. Must not be blank.
   * @param isOnDemand Optional parameter indicating whether to fetch data from cache or the external source.
   *                   If true, data will be fetched from the external source even if it's available in the cache.
   *                   If false or omitted, data will be fetched from the cache if available.
   * @return WeatherResponseDto containing the weather information for the specified city.
   * @throws IllegalArgumentException        If cityName is blank.
   * @throws ExternalServiceResponseException If there is an error fetching weather information from the external source.
   */
  @Override
  public WeatherResponseDto getWeatherInfo(final String cityName, final boolean isOnDemand) {
    validateCityName(cityName);

    final String cacheKey = cityName.toLowerCase();
    WeatherResponseDto response;

    if (isOnDemand || (response = weatherCacheService.get(cacheKey)) == null) {
      response = fetchWeatherInfoFromExternalSource(cityName)
        .orElseThrow(() -> new ExternalServiceResponseException("Failed to fetch weather info."));
      weatherCacheService.put(cacheKey, response);
    }

    return response;
  }

  /**
   * Validates the cityName parameter.
   *
   * @param cityName The name of the city to validate.
   * @throws IllegalArgumentException If cityName is blank.
   */
  private void validateCityName(final String cityName) {
    if (StringUtils.isBlank(cityName)) {
      throw new IllegalArgumentException("City name is required.");
    }
  }

  /**
   * Fetches weather information from the external source.
   *
   * @param cityName The name of the city for which to fetch weather information.
   * @return Optional containing the fetched WeatherResponseDto, or empty if fetching fails.
   * @throws ExternalServiceResponseException If there is an error fetching weather information from the external source.
   */
  private Optional<WeatherResponseDto> fetchWeatherInfoFromExternalSource(final String cityName) {
    try {
      final HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      final UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(props.getOpenweathermap().getApiUrl())
                                                               .queryParam("q", "{city}")
                                                               .queryParam("appid", "{apiKey}")
                                                               .queryParam("units", "metric");
      final String url = builder.buildAndExpand(cityName, props.getOpenweathermap().getApiKey()).toUriString();
      final ResponseEntity<WeatherResponseDto> responseEntity = restExternalService.exchange(
        url,
        Optional.empty(),
        headers,
        new ParameterizedTypeReference<>() {},
        HttpMethod.GET
      );
      return Optional.ofNullable(responseEntity.getBody());
    } catch (Exception e) {
      log.error("External Service error, couldn't retrieve data from :{}\n, exception message: {}, cause: {}, stackTrace: {}",
        props.getOpenweathermap().getApiUrl(),
        e.getMessage(),
        e.getCause(),
        e.getStackTrace());
      throw new ExternalServiceResponseException(ErrorCodes.INTERNAL_SERVER_ERROR.getMessage());
    }
  }
}
