package com.kameleoon.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {

  @Schema(title = "Path where the error occurred")
  String path;
  @Schema(title = "HTTP Status Code")
  int status;
  @Schema(title = "Error message")
  String error;
  @Schema(title = "Error text")
  String message;
  @Schema(title = "Error code")
  int code;

}
