package de.dfutil.dao;

import com.github.benmanes.caffeine.cache.Caffeine;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.List;

@AutoConfiguration
@EnableTransactionManagement
@EnableCaching
public class CommonsAppConfig {

    @Bean
    public JpaTransactionManager transactionManager(
            final EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public CacheManager cacheManager() {
        final SimpleCacheManager cacheManager = new SimpleCacheManager();
        final List<CaffeineCache> caches = Arrays.asList(
                new CaffeineCache("kgCache", Caffeine.newBuilder()
                        .build()),
                new CaffeineCache("obCache", Caffeine.newBuilder()
                        .build()),
                new CaffeineCache("orCache", Caffeine.newBuilder()
                        .build()),
                new CaffeineCache("plCache", Caffeine.newBuilder()
                        .build()),
                new CaffeineCache("sbCache", Caffeine.newBuilder()
                        .build())
        );
        cacheManager.setCaches(caches);
        return cacheManager;
    }

}