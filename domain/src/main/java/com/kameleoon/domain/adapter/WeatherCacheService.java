package com.kameleoon.domain.adapter;

import com.kameleoon.domain.service.CacheService;
import com.kameleoon.domain.dto.WeatherResponseDto;

/**
 * Interface representing a cache service for weather information.
 * Extends the generic CacheService interface with specific types for keys and values.
 * this interface used as a port between hazelcast-adapter
 */
public interface WeatherCacheService extends CacheService<String, WeatherResponseDto> {

}
