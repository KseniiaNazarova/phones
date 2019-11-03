package com.phone.site.api;

import java.util.Objects;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Tester info
 */
public class Tester
{
    @NotNull
    private final Long id;

    @NotBlank
    private final String firstName;

    @NotBlank
    private final String lastName;

    @NotBlank
    // todo @IsEmailValid
    private final String email;


    @Override
    public String toString()
    {
        return "Tester{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    @JsonCreator
    private Tester(@JsonProperty("id") final Long id,
                   @JsonProperty("firstName") final String firstName,
                   @JsonProperty("lastName") final String lastName,
                   @JsonProperty("email") final String email)
    {
        this.id = Objects.requireNonNull(id);
        this.firstName = Objects.requireNonNull(firstName);
        this.lastName = Objects.requireNonNull(lastName);
        this.email = Objects.requireNonNull(email);
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


    @JsonProperty("firstName")
    public String getFirstName()
    {
        return firstName;
    }


    @JsonProperty("lastName")
    public String getLastName()
    {
        return lastName;
    }


    @JsonProperty("email")
    public String getEmail()
    {
        return email;
    }


    public static final class Builder
    {
        private Long id;

        private String firstName;

        private String lastName;

        private String email;


        public Builder withId(final Long id)
        {
            this.id = id;
            return this;
        }


        public Builder withFirstName(final String firstName)
        {
            this.firstName = firstName;
            return this;
        }


        public Builder withLastName(final String lastName)
        {
            this.lastName = lastName;
            return this;
        }


        public Builder withEmail(final String email)
        {
            this.email = email;
            return this;
        }


        public Tester build()
        {
            return new Tester(id, firstName, lastName, email);
        }
    }
}
