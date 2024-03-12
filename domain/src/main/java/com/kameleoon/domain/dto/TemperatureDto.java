package com.kameleoon.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TemperatureDto implements Serializable {
  @Schema(title = "Current temperature in Celsius")
  String temp;
  @Schema(title = "Temperature feels like")
  String feelsLike;
}
