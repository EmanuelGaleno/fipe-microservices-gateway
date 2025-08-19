package com.r2r.fipe.gateway.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    // Por enquanto usa ConcurrentMapCache (in-memory).
    // No passo 1.9 trocaremos para Redis CacheManager.
}