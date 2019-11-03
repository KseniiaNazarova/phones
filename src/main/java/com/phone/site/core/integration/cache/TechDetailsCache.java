package com.phone.site.core.integration.cache;


import java.util.Optional;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.phone.site.core.dao.entity.ModelData;
import com.phone.site.core.integration.FonoapiIntegrationService;
import com.phone.site.core.integration.TechDetailsData;
import org.springframework.stereotype.Component;


/**
 * Caches technical detailes of a phone that we received from fonoapi
 */
@Component
public class TechDetailsCache
{
    private final LoadingCache<ModelData, Optional<TechDetailsData>> cache;


    public TechDetailsCache(
            final FonoapiIntegrationService fonoapiIntegrationService,
            final CacheProperties properties)
    {
        this.cache = CacheBuilder.newBuilder()
                .expireAfterWrite(properties.getDuration().toNanos(), TimeUnit.NANOSECONDS)
                .build(new CacheLoader<>()
                {
                    @Override
                    public Optional<TechDetailsData> load(final ModelData data)
                    {
                        return fonoapiIntegrationService.getPhoneTechDetails(data);
                    }
                });
    }


    public Optional<TechDetailsData> get(final ModelData data)
    {
        return cache.getUnchecked(data);
    }
}
