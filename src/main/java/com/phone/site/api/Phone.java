package com.phone.site.api;


import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Api view for a tested phone
 */
public class Phone
{
    private final Long id;

    private final Model model;

    private final Boolean isAvailable;

    private final TechDetails techDetails;


    @JsonCreator
    private Phone(@JsonProperty("id") final Long id,
                  @JsonProperty("model") final Model model,
                  @JsonProperty("isAvailable") final Boolean isAvailable,
                  @JsonProperty("techDetails") final TechDetails techDetails)
    {
        this.id = Objects.requireNonNull(id);
        this.model = Objects.requireNonNull(model);
        this.isAvailable = Objects.requireNonNull(isAvailable);
        this.techDetails = Objects.requireNonNull(techDetails);
    }


    public static Builder builder()
    {
        return new Builder();
    }


    @JsonProperty("id")
    public Long getId()
    {
        return id;
    }


    @JsonProperty("model")
    public Model getModel()
    {
        return model;
    }


    @JsonProperty("isAvailable")
    public Boolean isAvailable()
    {
        return isAvailable;
    }


    @JsonProperty("techDetails")
    public TechDetails getTechDetails()
    {
        return techDetails;
    }


    public static final class Builder
    {
        private Long id;

        private Model model;

        private Boolean isAvailable;

        private TechDetails techDetails;


        public Builder withId(final Long id)
        {
            this.id = id;
            return this;
        }


        public Builder withModel(final Model model)
        {
            this.model = model;
            return this;
        }


        public Builder isAvailable(final Boolean isAvailable)
        {
            this.isAvailable = isAvailable;
            return this;
        }


        public Builder withTechDetails(final TechDetails techDetails)
        {
            this.techDetails = techDetails;
            return this;
        }


        public Phone build()
        {
            return new Phone(id, model, isAvailable, techDetails);
        }
    }


    @Override
    public String toString()
    {
        return "Phone{" +
                "id=" + id +
                ", model=" + model +
                ", isAvailable='" + isAvailable + '\'' +
                ", techDetails=" + techDetails +
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
        final Phone phone = (Phone) o;
        return Objects.equals(id, phone.id) &&
                Objects.equals(model, phone.model) &&
                Objects.equals(isAvailable, phone.isAvailable) &&
                Objects.equals(techDetails, phone.techDetails);
    }


    @Override
    public int hashCode()
    {
        return Objects.hash(id, model, isAvailable, techDetails);
    }
}
