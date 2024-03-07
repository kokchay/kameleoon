package com.kameleoon.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@AllArgsConstructor
public class WeatherController {
  @GetMapping
  public ResponseEntity<Object> something() {
    return ResponseEntity.ok("Something"); // todo
  }
}
