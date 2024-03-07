package com.kameleoon.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Response {

  @JsonProperty(value = "weather")
  @Schema(title = "Weather description")
  WeatherDto weather;

  @JsonProperty(value = "temperature")
  @Schema(title = "Temperature description")
  TemperatureDto temperature;

  @JsonProperty(value = "visibility")
  @Schema(title = "Visibility description")
  Long visibility;

  @JsonProperty(value = "wind")
  @Schema(title = "Wind description")
  WindDto wind;

  @JsonProperty(value = "datetime")
  @Schema(title = "DateTime description")
  LocalDateTime dateTime;

  @JsonProperty(value = "sys")
  @Schema(title = "Sunset/Sunrise description")
  SysDto sys;

  @JsonProperty(value = "timezone")
  @Schema(title = "TimeZone description")
  String timezone;

  @JsonProperty(value = "name")
  @Schema(title = "Name")
  String name;

}
