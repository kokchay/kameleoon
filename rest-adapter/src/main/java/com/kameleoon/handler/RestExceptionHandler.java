package com.kameleoon.handler;

import com.kameleoon.domain.dto.ErrorResponse;
import com.kameleoon.domain.exception.AbstractBusinessLogicException;
import com.kameleoon.domain.exception.BusinessLogicException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.Optional;

//todo
@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  private static final String DEFAULT_MESSAGE = "Unknown error";
  private static final int DEFAULT_CODE = 999;
  private static final int ILLEGAL_ARGUMENT_CODE = 888;

  @ResponseBody
  @ExceptionHandler(IOException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> invalidInputHandler(final HttpServletRequest request,
                                                           final HttpServletResponse response,
                                                           final Exception ex) {
    return new ResponseEntity<>(ErrorResponse.builder()
                                             .path(request.getServletPath())
                                             .status(HttpStatus.BAD_REQUEST.value())
                                             .code(ILLEGAL_ARGUMENT_CODE)
                                             .message(ex.getMessage())
                                             .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                             .build(),
                                HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex,
                                                                       final HttpHeaders headers,
                                                                       final HttpStatusCode status,
                                                                       final WebRequest request) {
    return new ResponseEntity<>(ErrorResponse.builder()
                                             .path(request.getContextPath())
                                             .status(HttpStatus.BAD_REQUEST.value())
                                             .code(405)
                                             .message("Method not allowed")
                                             .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                             .build(),
                                HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(final HttpMediaTypeNotAcceptableException ex,
                                                                    final HttpHeaders headers,
                                                                    final HttpStatusCode status,
                                                                    final WebRequest request) {
    return new ResponseEntity<>(ErrorResponse.builder()
                                             .path(request.getContextPath())
                                             .status(HttpStatus.BAD_REQUEST.value())
                                             .code(415)
                                             .message("Media type not supported")
                                             .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                             .build(),
                                HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex,
                                                                        final HttpHeaders headers,
                                                                        final HttpStatusCode status,
                                                                        final WebRequest request) {
    return new ResponseEntity<>(ErrorResponse.builder()
                                             .path(request.getContextPath())
                                             .status(HttpStatus.BAD_REQUEST.value())
                                             .code(406)
                                             .message("Parameter not acceptable")
                                             .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                             .build(),
                                HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
                                                                 final HttpHeaders headers,
                                                                 final HttpStatusCode status,
                                                                 final WebRequest request) {
    return new ResponseEntity<>(ErrorResponse.builder()
                                             .path(request.getContextPath())
                                             .status(HttpStatus.BAD_REQUEST.value())
                                             .code(404)
                                             .message("Path not found")
                                             .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                             .build(),
                                HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
                                                                   final HttpHeaders headers,
                                                                   final HttpStatusCode status,
                                                                   final WebRequest request) {
    return new ResponseEntity<>(ErrorResponse.builder()
                                             .path(request.getContextPath())
                                             .status(HttpStatus.BAD_REQUEST.value())
                                             .code(407)
                                             .message("Missing parameter")
                                             .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                             .build(),
                                HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                final HttpHeaders headers,
                                                                final HttpStatusCode status,
                                                                final WebRequest request) {
    return new ResponseEntity<>(ErrorResponse.builder()
                                             .path(request.getContextPath())
                                             .status(HttpStatus.BAD_REQUEST.value())
                                             .code(422)
                                             .message("Arguments not valid")
                                             .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                             .build(),
                                HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(final Exception ex,
                                                           final Object body,
                                                           final HttpHeaders headers,
                                                           final HttpStatusCode status,
                                                           final WebRequest request) {
    return new ResponseEntity<>(ErrorResponse.builder()
                                             .path(request.getContextPath())
                                             .status(HttpStatus.BAD_REQUEST.value())
                                             .code(500)
                                             .message(DEFAULT_MESSAGE)
                                             .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                             .build(),
                                HttpStatus.BAD_REQUEST);
  }

  @ResponseBody
  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> illegalArgumentHandler(final HttpServletRequest request,
                                                              final IllegalArgumentException ex) {
    return new ResponseEntity<>(ErrorResponse.builder()
                                             .path(request.getServletPath())
                                             .status(HttpStatus.BAD_REQUEST.value())
                                             .code(ILLEGAL_ARGUMENT_CODE)
                                             .message(ex.getMessage())
                                             .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                             .build(),
                                HttpStatus.BAD_REQUEST);
  }

  @ResponseBody
  @ExceptionHandler(AbstractBusinessLogicException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ResponseEntity<ErrorResponse> handleException(final HttpServletRequest request,
                                                       final BusinessLogicException ex) {
    final String message = Optional.of(ex)
                                   .map(BusinessLogicException.class::cast)
                                   .map(BusinessLogicException::getMessage)
                                   .orElse(DEFAULT_MESSAGE);
    final int code = Optional.of(ex)
                             .map(BusinessLogicException.class::cast)
                             .map(BusinessLogicException::getCode)
                             .orElse(DEFAULT_CODE);
    return new ResponseEntity<>(ErrorResponse.builder()
                                             .path(request.getServletPath())
                                             .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                             .code(code)
                                             .message(message)
                                             .error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                             .build(),
                                HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
