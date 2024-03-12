package com.kameleoon.domain.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeatherResponseDto implements Serializable {

  @JsonProperty(value = "weather")
  @Schema(title = "Weather description")
  @NotNull(message = "weather must not be null")
  WeatherDto weather;

  @Schema(title = "Temperature description")
  @NotNull(message = "temperature must not be null")
  @JsonAlias({"main"})
  TemperatureDto temperature;

  @JsonAlias({"visibility"})
  @Schema(title = "Visibility description")
  @NotNull(message = "visibility must not be null")
  Long visibility;

  @JsonAlias({"wind"})
  @Schema(title = "Wind description")
  @NotNull(message = "wind must not be null")
  WindDto wind;

  @JsonAlias({"dt"})
  @Schema(title = "DateTime description")
  @NotNull(message = "dateTime must not be null")
  Timestamp dateTime;

  @JsonAlias({"sys"})
  @Schema(title = "Sunset/Sunrise description")
  @NotNull(message = "sys must not be null")
  SysDto sys;

  @JsonAlias({"timezone"})
  @Schema(title = "Timezone")
  @NotNull(message = "timezone must not be null")
  String timezone;

  @JsonAlias({"name"})
  @Schema(title = "City Name")
  @NotNull(message = "name must not be null")
  String name;

}
