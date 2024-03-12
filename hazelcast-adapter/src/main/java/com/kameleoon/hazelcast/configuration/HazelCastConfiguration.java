package com.kameleoon.hazelcast.configuration;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.NearCacheConfig;
import com.hazelcast.core.HazelcastInstance;
import com.kameleoon.domain.configuraiton.WeatherConfigurationProperties;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.hazelcast.client.HazelcastClient.newHazelcastClient;

/**
 * Configuration class for setting up Hazelcast client instance.
 * This class provides configuration for Hazelcast client including near cache configuration.
 */
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class HazelCastConfiguration {

  /**
   * Configuration properties for Hazelcast.
   */
  WeatherConfigurationProperties props;

  /**
   * Configures the Hazelcast client.
   *
   * @return ClientConfig The configured client configuration.
   */
  @Bean
  public ClientConfig clientConfig() {
    final ClientConfig config = new ClientConfig();
    config.addNearCacheConfig(createNearCacheConfig());
    config.setClusterName(props.getHazelcast().getClusterName());
    config.getNetworkConfig().addAddress(props.getHazelcast().getHost());
    config.getNetworkConfig().setSmartRouting(false);
    return config;
  }

  /**
   * Creates the Hazelcast client instance.
   *
   * @return HazelcastInstance The Hazelcast client instance.
   */
  @Bean
  public HazelcastInstance hazelcastInstance() {
    return newHazelcastClient(clientConfig());
  }

  /**
   * Creates the near cache configuration.
   *
   * @return NearCacheConfig The near cache configuration.
   */
  private NearCacheConfig createNearCacheConfig() {
    NearCacheConfig nearCacheConfig = new NearCacheConfig();
    nearCacheConfig.setName(props.getHazelcast().getCacheName());
    nearCacheConfig.setTimeToLiveSeconds(props.getHazelcast().getCacheDefaultTtl());
    nearCacheConfig.setMaxIdleSeconds(props.getHazelcast().getMaxIdleSeconds());
    return nearCacheConfig;
  }
}
