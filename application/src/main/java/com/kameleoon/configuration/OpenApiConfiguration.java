package com.kameleoon.configuration;

import com.kameleoon.domain.configuraiton.WeatherConfigurationProperties;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
  type = SecuritySchemeType.HTTP,
  name = "basicAuth",
  scheme = "basic")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OpenApiConfiguration {

  WeatherConfigurationProperties props;
  @Bean
  public OpenAPI customAPI() {
    return new OpenAPI()
      .servers(props.getServers().stream()
        .map(i -> new Server()
          .url(i.getUrl())
          .description(i.getDescription()))
        .toList());
  }
}
