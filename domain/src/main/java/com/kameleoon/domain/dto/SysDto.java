package com.kameleoon.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SysDto implements Serializable {
  @Schema(title = "Sunrise")
  String sunrise;
  @Schema(title = "Sunset")
  String sunset;
}
