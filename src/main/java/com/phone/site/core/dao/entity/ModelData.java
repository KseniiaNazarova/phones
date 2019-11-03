package com.phone.site.core.dao.entity;

import java.util.Objects;

public class ModelData
{
    private final String brand;

    private final String model;


    private ModelData(final String brand, final String model)
    {
        this.brand = Objects.requireNonNull(brand);
        this.model = Objects.requireNonNull(model);
    }


    public static Builder builder()
    {
        return new Builder();
    }


    public String getBrand()
    {
        return brand;
    }


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


        public ModelData build()
        {
            return new ModelData(brand, model);
        }
    }


    @Override
    public String toString()
    {
        return "ModelData{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }


    @Override
    public boolean equals(final Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        final ModelData modelData = (ModelData) o;
        return Objects.equals(brand, modelData.brand) &&
                Objects.equals(model, modelData.model);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(brand, model);
    }
}
