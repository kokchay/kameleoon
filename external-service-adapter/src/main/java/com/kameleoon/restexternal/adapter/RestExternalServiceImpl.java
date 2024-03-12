package com.kameleoon.restexternal.adapter;

import com.kameleoon.domain.adapter.RestExternalService;
import com.kameleoon.domain.exception.ExternalServiceResponseException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * Implementation of the {@link RestExternalService} interface, facilitating HTTP exchanges with external services.
 * This class uses the provided RestTemplate to make HTTP requests and handle responses.
 */
@RequiredArgsConstructor
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class RestExternalServiceImpl implements RestExternalService {

  RestTemplate restTemplate;

  /**
   * Perform an HTTP exchange with the external service.
   *
   * @param url             The URL of the external service.
   * @param request         The request body, wrapped in an Optional.
   * @param headers         The HTTP headers for the request.
   * @param response        The expected response type.
   * @param method          The HTTP method to use.
   * @param uriVariables    Variables to expand in the URL, if any.
   * @param <T>             The type of the response body.
   * @return ResponseEntity containing the response body and status code.
   * @throws ExternalServiceResponseException If there is an error during the HTTP exchange.
   */
  @Override
  public <T> ResponseEntity<T> exchange(final String url,
                                        final Optional<Object> request,
                                        final HttpHeaders headers,
                                        final ParameterizedTypeReference<T> response,
                                        final HttpMethod method,
                                        final Object... uriVariables) {
    return exchange(restTemplate, url, request, headers, response, method, uriVariables);
  }

  /**
   * Perform an HTTP exchange with the external service using the provided RestTemplate.
   *
   * @param restTemplate    The RestTemplate instance to use for the exchange.
   * @param url             The URL of the external service.
   * @param request         The request body.
   * @param headers         The HTTP headers for the request.
   * @param response        The expected response type.
   * @param method          The HTTP method to use.
   * @param uriVariables    Variables to expand in the URL, if any.
   * @param <T>             The type of the response body.
   * @return ResponseEntity containing the response body and status code.
   * @throws ExternalServiceResponseException If there is an error during the HTTP exchange.
   */
  private <T> ResponseEntity<T> exchange(final RestTemplate restTemplate,
                                         final String url,
                                         final Object request,
                                         final HttpHeaders headers,
                                         final ParameterizedTypeReference<T> response,
                                         final HttpMethod method,
                                         final Object... uriVariables) {
    try {
      final HttpEntity<Object> httpEntity = new HttpEntity<>(request, headers);
      final ResponseEntity<T> result = restTemplate.exchange(url, method, httpEntity, response, uriVariables);
      return Optional.of(result).orElseThrow(ExternalServiceResponseException::new);
    } catch (final RestClientException ex) {
      throw new ExternalServiceResponseException(ex.getMessage());
    }
  }
}
