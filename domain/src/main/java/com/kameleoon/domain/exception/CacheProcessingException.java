package com.kameleoon.domain.exception;

import com.kameleoon.domain.common.ErrorCodes;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CacheProcessingException extends AbstractBusinessLogicException {

  int code;
  String message;

  public CacheProcessingException() {
    this.code = ErrorCodes.CACHE_PROCESSING_ERROR.getCode();
    this.message = ErrorCodes.CACHE_PROCESSING_ERROR.getMessage();
  }

  public CacheProcessingException(final int code, final String message) {
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
