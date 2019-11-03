package com.phone.site.core.integration;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TechDetailsRequest
{
    private final String brand;

    private final String device;

    private final String token;


    @JsonCreator
    private TechDetailsRequest(@JsonProperty("brand") final String brand,
                               @JsonProperty("device") final String device,
                               @JsonProperty("token") final String token)
    {
        this.brand = Objects.requireNonNull(brand);
        this.device = Objects.requireNonNull(device);
        this.token = Objects.requireNonNull(token);
    }


    public static Builder builder()
    {
        return new Builder();
    }


    @JsonProperty("brand")
    public String getBrand()
    {
        return brand;
    }


    @JsonProperty("device")
    public String getDevice()
    {
        return device;
    }


    @JsonProperty("token")
    public String getToken()
    {
        return token;
    }


    public static final class Builder
    {
        private String brand;

        private String device;

        private String token;


        public Builder withBrand(final String brand)
        {
            this.brand = brand;
            return this;
        }


        public Builder withDevice(final String device)
        {
            this.device = device;
            return this;
        }


        public Builder withToken(final String token)
        {
            this.token = token;
            return this;
        }


        public TechDetailsRequest build()
        {
            return new TechDetailsRequest(brand, device, token);
        }
    }


    @Override
    public String toString()
    {
        return "TechDetailsRequest{" +
                "brand='" + brand + '\'' +
                ", device='" + device + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
