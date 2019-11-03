package com.phone.site.api;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Tech details about a phone
 * received from fonoapi
 */
public class TechDetails
{
    private final String technology;

    private final String bands2g;

    private final String bands3g;

    private final String bands4g;


    @Override
    public String toString()
    {
        return "TechDetails{" +
                "technology='" + technology + '\'' +
                ", bands2g='" + bands2g + '\'' +
                ", bands3g='" + bands3g + '\'' +
                ", bands4g='" + bands4g + '\'' +
                '}';
    }


    @JsonCreator
    private TechDetails(@JsonProperty("technology") final String technology,
                        @JsonProperty("bands2g") final String bands2g,
                        @JsonProperty("bands3g") final String bands3g,
                        @JsonProperty("bands4g") final String bands4g)
    {
        this.technology = Objects.requireNonNull(technology);
        this.bands2g = Objects.requireNonNull(bands2g);
        this.bands3g = Objects.requireNonNull(bands3g);
        this.bands4g = Objects.requireNonNull(bands4g);
    }


    public static Builder builder()
    {
        return new Builder();
    }


    @JsonProperty("technology")
    public String getTechnology()
    {
        return technology;
    }


    @JsonProperty("bands2g")
    public String getBands2g()
    {
        return bands2g;
    }


    @JsonProperty("bands3g")
    public String getBands3g()
    {
        return bands3g;
    }


    @JsonProperty("bands4g")
    public String getBands4g()
    {
        return bands4g;
    }


    public static final class Builder
    {
        private String technology;

        private String bands2g;

        private String bands3g;

        private String bands4g;


        public Builder withTechnology(final String technology)
        {
            this.technology = technology;
            return this;
        }


        public Builder withBands2g(final String bands2g)
        {
            this.bands2g = bands2g;
            return this;
        }


        public Builder withBands3g(final String bands3g)
        {
            this.bands3g = bands3g;
            return this;
        }


        public Builder withBands4g(final String bands4g)
        {
            this.bands4g = bands4g;
            return this;
        }


        public TechDetails build()
        {
            return new TechDetails(technology, bands2g, bands3g, bands4g);
        }
    }
}
