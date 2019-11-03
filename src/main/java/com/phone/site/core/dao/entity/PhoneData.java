package com.phone.site.core.dao.entity;

import java.util.Objects;

public class PhoneData
{
    private final Long id;

    private final ModelData modelData;

    private final boolean isAvailable;


    private PhoneData(final Long id, final ModelData modelData, final boolean isAvailable)
    {
        this.id = Objects.requireNonNull(id);
        this.modelData = Objects.requireNonNull(modelData);
        this.isAvailable = isAvailable;
    }


    public static Builder builder()
    {
        return new Builder();
    }


    public Long getId()
    {
        return id;
    }


    public ModelData getModelData()
    {
        return modelData;
    }


    public boolean isAvailable()
    {
        return isAvailable;
    }


    public static final class Builder
    {
        private Long id;

        private ModelData modelData;

        private boolean isAvailable;


        public Builder withId(final Long id)
        {
            this.id = id;
            return this;
        }


        public Builder withModelData(final ModelData modelData)
        {
            this.modelData = modelData;
            return this;
        }


        public Builder isAvailable(final boolean isAvailable)
        {
            this.isAvailable = isAvailable;
            return this;
        }


        public PhoneData build()
        {
            return new PhoneData(id, modelData, isAvailable);
        }
    }


    @Override
    public String toString()
    {
        return "PhoneData{" +
                "id=" + id +
                ", modelData=" + modelData +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
