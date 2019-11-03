package com.phone.site.core.integration.cache;

import java.time.Duration;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@Validated
@ConfigurationProperties(prefix = "fonoapi.cache")
public class CacheProperties
{
    @NotNull
    private Duration duration;


    public Duration getDuration()
    {
        return duration;
    }


    public void setDuration(final Duration duration)
    {
        this.duration = duration;
    }
}
