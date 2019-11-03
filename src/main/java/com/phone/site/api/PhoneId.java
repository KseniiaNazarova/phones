package com.phone.site.api;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * View for phone id
 */
public class PhoneId
{
    private final Long id;


    @JsonCreator
    private PhoneId(@JsonProperty("id") final Long id)
    {
        this.id = Objects.requireNonNull(id);
    }


    public static PhoneId of(final Long id)
    {
        return new PhoneId(id);
    }


    @JsonProperty("id")
    public Long asLong()
    {
        return id;
    }


    @Override
    public String toString()
    {
        return "PhoneId{" +
                "id=" + id +
                '}';
    }
}
