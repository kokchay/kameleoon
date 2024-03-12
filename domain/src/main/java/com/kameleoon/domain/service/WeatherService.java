package com.kameleoon.domain.service;

import com.kameleoon.domain.dto.WeatherResponseDto;

public interface WeatherService {
  WeatherResponseDto getWeatherInfo(String cityName, boolean isOnDemand);
}
