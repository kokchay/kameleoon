

package com.kameleoon.domain.adapter;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

/**
 * Interface representing a REST external service.
 * this interface used as a port between external=service-adapter
 */
public interface RestExternalService {
    /**
     * Perform an HTTP exchange with the external service.
     *
     * @param url             the URL of the external service
     * @param request         the request body, wrapped in an Optional
     * @param headers         the HTTP headers for the request
     * @param response        the expected response type
     * @param method          the HTTP method to use
     * @param uriVariables    variables to expand in the URL, if any
     * @param <T>             the type of the response body
     * @return the ResponseEntity containing the response body and status code
     */
    <T> ResponseEntity<T> exchange(String url, Optional<Object> request, HttpHeaders headers, ParameterizedTypeReference<T> response, HttpMethod method, Object... uriVariables);
}
