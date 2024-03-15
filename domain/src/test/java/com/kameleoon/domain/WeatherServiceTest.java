//package com.kameleoon.domain;
//
//import com.kameleoon.domain.adapter.WeatherCacheService;
//import com.kameleoon.domain.service.WeatherService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//@ExtendWith(MockitoExtension.class)
//public class WeatherServiceTest {
//  WeatherService weatherService;
//
//  @BeforeEach
//  void init(@Mock WeatherCacheService cacheService) {
//    weatherService = new DefaultUserService(userRepository, settingRepository, mailClient);
//
//    lenient().when(settingRepository.getUserMinAge()).thenReturn(10);
//
//    when(settingRepository.getUserNameMinLength()).thenReturn(4);
//
//    lenient().when(userRepository.isUsernameAlreadyExists(any(String.class)))
//      .thenReturn(false);
//  }
//}
