package com.phone.site.core.integration;

import java.util.Optional;

import com.phone.site.core.dao.entity.ModelData;

/**
 * Interface for integration with external services to get phone tech details
 */
public interface IntegrationService
{
    Optional<TechDetailsData> getPhoneTechDetails(final ModelData modelData);
}
