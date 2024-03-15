package com.kameleoon.domain.exception;

import com.kameleoon.domain.common.ErrorCodes;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ExternalServiceResponseException extends AbstractBusinessLogicException {

  int code;
  String message;

  public ExternalServiceResponseException() {
    this.code = ErrorCodes.INTERNAL_SERVER_ERROR.getCode();
    this.message = ErrorCodes.INTERNAL_SERVER_ERROR.getMessage();
  }

  public ExternalServiceResponseException(final String message) {
    this(ErrorCodes.INTERNAL_SERVER_ERROR.getCode(), message);
  }

  public ExternalServiceResponseException(final int code) {
    this(code, ErrorCodes.INTERNAL_SERVER_ERROR.getMessage());
  }

  public ExternalServiceResponseException(final int code, final String message) {
    this.code = code;
    this.message = message;
  }

  @Override
  public int getCode() {
    return code;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
