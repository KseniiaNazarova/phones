package com.phone.site.api;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * View for tester id
 */
public class TesterId
{
    private final Long id;


    @JsonCreator
    private TesterId(@JsonProperty("id") final Long id)
    {
        this.id = Objects.requireNonNull(id);
    }


    public static TesterId of(final Long id)
    {
        return new TesterId(id);
    }


    @JsonProperty("id")
    public Long asLong()
    {
        return id;
    }


    @Override
    public String toString()
    {
        return "TesterId{" +
                "id=" + id +
                '}';
    }
}
