/**
 * @apiNote this component will provide environment properties from application.yml file
 * */

package com.kameleoon.domain.configuraiton;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@ConfigurationProperties("weather")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WeatherConfigurationProperties {

    List<Server> servers = new ArrayList<>();
    Hazelcast hazelcast;
    Openweathermap openweathermap;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Server {
        String url;
        String description;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Hazelcast {
        String host;
        String clusterName;
        String cacheName;
        Integer cacheDefaultTtl;
        Integer maxIdleSeconds;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Openweathermap {
        String apiKey;
        String apiUrl;
    }

}
