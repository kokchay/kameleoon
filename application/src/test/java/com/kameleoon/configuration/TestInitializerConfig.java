package com.kameleoon.configuration;

import com.hazelcast.testcontainers.HazelcastContainer;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
public abstract class TestInitializerConfig {

  @Autowired
  protected MockMvc mvc;
//  private static final HazelcastContainer hazelcastContainer = new HazelcastContainer("5.2.0-slim")
//    .withExposedPorts(5700);
//
//  static {
//    hazelcastContainer.start();
//    assertTrue(hazelcastContainer.isRunning());
//  }
//
//  @DynamicPropertySource
//  public static void setUpDynamicPropertyValues(DynamicPropertyRegistry registry) {
//    registry.add("hazelcast.cluster.host", () -> hazelcastContainer.getHost() + ":" + hazelcastContainer.getFirstMappedPort());
//    registry.add("hazelcast.cluster.name", () -> "dev");
//  }

}
