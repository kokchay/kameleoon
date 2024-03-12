package com.kameleoon.domain.service;

import java.util.Optional;

public interface CacheService<K, V> {
  V put(K key, V val);

  V get(K key);
}
