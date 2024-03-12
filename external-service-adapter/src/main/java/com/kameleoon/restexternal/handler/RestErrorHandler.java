package com.kameleoon.restexternal.handler;

import com.kameleoon.domain.exception.ExternalServiceResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.URI;

@Slf4j
public class RestErrorHandler implements ResponseErrorHandler {

  /**
   * Handles error response from the server.
   *
   * @param clientHttpResponse The HTTP response received from the server.
   * @throws IOException If an I/O error occurs while handling the error.
   */
  @Override
  public void handleError(ClientHttpResponse clienthttpresponse) throws IOException {

    if (clienthttpresponse.getStatusCode() == HttpStatus.FORBIDDEN) {
      log.debug(HttpStatus.BAD_REQUEST + " response. Try to construct your request correctly");
      throw new ExternalServiceResponseException();
    }
  }

  /**
   * Checks if the HTTP response indicates an error.
   *
   * @param clienthttpresponse The HTTP response received from the server.
   * @return True if the response indicates an error, otherwise false.
   * @throws IOException If an I/O error occurs while checking the error.
   */
  @Override
  public boolean hasError(ClientHttpResponse clienthttpresponse) throws IOException {

    if (clienthttpresponse.getStatusCode() != HttpStatus.OK) {
      log.error("Status code: {}", clienthttpresponse.getStatusCode());
      log.error("Response... {}", clienthttpresponse.getStatusText());
      log.error("Body: {}", clienthttpresponse.getBody());

      return true;
    }
    return false;
  }

  /**
   * Handles error response from the server.
   *
   * @param url      The URL of the request.
   * @param method   The HTTP method of the request.
   * @param response The HTTP response received from the server.
   * @throws IOException If an I/O error occurs while handling the error.
   */
  @Override
  public void handleError(final URI url,
                          final HttpMethod method,
                          final ClientHttpResponse response) throws IOException {
    ResponseErrorHandler.super.handleError(url, method, response);
  }
}
