package com.kameleoon.domain.common;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public enum ErrorCodes {

  INTERNAL_SERVER_ERROR(505, "Failed with getting result from external service"),
  CACHE_PROCESSING_ERROR(510, "Failed with getting result from cache");
  /**
   * @implNote this enum class provides error status code with related error message
   * @implSpec if you want to upgrade, or add other business-logic specific exceptions,
   *           then add your specific status code with custom message
   * */
  int code;
  String message;
}
