package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource({"classpath:common.properties"})
public class PropertyConfig
{
    static @Bean
    public PropertySourcesPlaceholderConfigurer myPropertySourcesPlaceholderConfigurer()
    {
        return new PropertySourcesPlaceholderConfigurer();
    }

    /**
     * Properties to support the 'test' mode of operation.
     */
    @Configuration
    @Profile("test")
    @PropertySource("classpath:env-dev.properties")
    static class Test
    {
    }


    /**
     * Properties to support the 'test' mode of operation.
     */
    @Configuration
    @Profile({"dev"})
    @PropertySource("classpath:env-dev.properties")
    static class Dev
    {
    }

    /**
     * Properties to support the 'production' mode of operation.
     */
    @Configuration
    @Profile("production")
    @PropertySources({
        @PropertySource(value = "classpath:env-production.properties", ignoreResourceNotFound = true),
        @PropertySource(value = "file:${CONF_DIR}/env-production.properties", ignoreResourceNotFound = true)
    })
    static class Production
    {
        // Define additional beans for this profile here
    }
}
