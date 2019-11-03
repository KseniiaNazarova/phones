package com.phone.site.api;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * View of a phone model
 */
public class Model
{
    private final String brand;

    private final String model;


    @JsonCreator
    private Model(@JsonProperty("brand") final String brand,
                  @JsonProperty("model") final String model)
    {
        this.brand = Objects.requireNonNull(brand);
        this.model = Objects.requireNonNull(model);
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


    @JsonProperty("model")
    public String getModel()
    {
        return model;
    }


    public static final class Builder
    {
        private String brand;

        private String model;


        public Builder withBrand(final String brand)
        {
            this.brand = brand;
            return this;
        }


        public Builder withModel(final String model)
        {
            this.model = model;
            return this;
        }


        public Model build()
        {
            return new Model(brand, model);
        }
    }


    @Override
    public String toString()
    {
        return "ModelView{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
