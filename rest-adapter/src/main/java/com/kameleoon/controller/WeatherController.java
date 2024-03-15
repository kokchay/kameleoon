package com.kameleoon.controller;

import com.kameleoon.domain.dto.WeatherResponseDto;
import com.kameleoon.domain.service.WeatherServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class WeatherController {

  WeatherServiceImpl weatherServiceImpl;

  @Operation(summary = "Get weather information by city")
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "City's Weather information found",
      content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = WeatherResponseDto.class)) }),
    @ApiResponse(responseCode = "400", description = "Invalid data supplied",
      content = @Content),
    @ApiResponse(responseCode = "404", description = "City not found",
      content = @Content) })
  @GetMapping("{city}")
  public ResponseEntity<WeatherResponseDto> weather(@PathVariable(value = "city") final String city,
                                                      @RequestParam(required = false, name = "on-demand") Boolean isOnDemand) {

    return ResponseEntity.ok(weatherServiceImpl.getWeatherInfo(city, isOnDemand != null && isOnDemand));
  }
}
