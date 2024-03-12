
/**
 * Implementation of the WeatherCacheService interface using Hazelcast for caching weather information.
 */
package com.kameleoon.hazelcast.adapter;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.kameleoon.domain.adapter.WeatherCacheService;
import com.kameleoon.domain.configuraiton.WeatherConfigurationProperties;
import com.kameleoon.domain.dto.WeatherResponseDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Component
public class WeatherCacheServiceImpl implements WeatherCacheService {

  final WeatherConfigurationProperties props;
  final HazelcastInstance hazelcastInstance;


  /**
   * Put weather information into the cache for a given city.
   *
   * @param city    the name of the city
   * @param weather the weather information to cache
   * @return the previously cached weather information for the city, if any
   */
  @Override
  public WeatherResponseDto put(final String city, final WeatherResponseDto weather) {
    final IMap<String, WeatherResponseDto> cache = hazelcastInstance.getMap(props.getHazelcast().getCacheName());
    return cache.put(city, weather);
  }

  /**
   * Retrieve weather information from the cache for a given city.
   *
   * @param city the name of the city
   * @return the cached weather information for the city, if available
   */
  @Override
  public WeatherResponseDto get(final String city) {
    final IMap<String, WeatherResponseDto> cache = hazelcastInstance.getMap(props.getHazelcast().getCacheName());
    return cache.get(city);
  }

}
