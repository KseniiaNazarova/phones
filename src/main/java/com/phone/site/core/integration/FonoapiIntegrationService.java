package com.phone.site.core.integration;

import java.util.Collections;
import java.util.Optional;

import com.phone.site.core.SerializationUtils;
import com.phone.site.core.dao.entity.ModelData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FonoapiIntegrationService implements IntegrationService
{
    private static final Logger logger = LoggerFactory.getLogger(FonoapiIntegrationService.class);

    private static final String GET_DEVICE_URL = "https://fonoapi.freshpixl.com/v1/getdevice";

    private final FonoapiIntegrationProperties properties;

    private final RestTemplate restTemplate;


    public FonoapiIntegrationService(final FonoapiIntegrationProperties properties,
                                     final RestTemplate restTemplate)
    {
        this.properties = properties;
        this.restTemplate = restTemplate;
    }


    @Override
    public Optional<TechDetailsData> getPhoneTechDetails(final ModelData modelData)
    {
        final TechDetailsRequest request = TechDetailsRequest.builder()
                .withToken(properties.getToken())
                .withDevice(modelData.getModel())
                .withBrand(modelData.getBrand())
                .build();

        logger.debug("Request's sent to fonoapi: request={}", request);

        final HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        final HttpEntity<String> entity = new HttpEntity<>(SerializationUtils.toJson(request), headers);

        final ResponseEntity<String> result = restTemplate.exchange(
                GET_DEVICE_URL,
                HttpMethod.GET,
                entity,
                String.class);

        logger.info("fonoapi response: request={}, result={}", request, result);
        if (HttpStatus.OK == result.getStatusCode())
        {
            return Optional.ofNullable(SerializationUtils.fromJson(result.getBody(), TechDetailsData.class));
        }
        return Optional.empty();
    }
}
