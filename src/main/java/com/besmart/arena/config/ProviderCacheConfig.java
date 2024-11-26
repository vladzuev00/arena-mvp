package com.besmart.arena.config;

import com.besmart.arena.service.cache.ProviderCache;
import com.besmart.arena.service.cache.factory.ProviderCacheFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//TODO: test
@Configuration
public class ProviderCacheConfig {

    @Bean
    public ProviderCache create(ProviderCacheFactory factory) {
        return factory.create();
    }
}
