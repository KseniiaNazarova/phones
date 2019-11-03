package com.phone.site.core.integration;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

@Configuration
@Validated
@ConfigurationProperties(prefix = "fonoapi.integration")
public class FonoapiIntegrationProperties
{
    @NotNull
    private String token;

    private int position;


    public int getPosition()
    {
        return position;
    }


    public void setPosition(final int position)
    {
        this.position = position;
    }


    public String getToken()
    {
        return token;
    }


    public void setToken(final String token)
    {
        this.token = token;
    }
}
