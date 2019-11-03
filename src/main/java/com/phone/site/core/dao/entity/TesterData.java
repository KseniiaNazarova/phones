package com.phone.site.core.dao.entity;

import java.io.Serializable;
import java.util.Objects;

public class TesterData implements Serializable
{
    private final Long id;

    private final String firstName;

    private final String lastName;

    private final String email;


    private TesterData(final Long id, final String firstName, final String lastName, final String email)
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


    public Long getId()
    {
        return id;
    }


    public String getFirstName()
    {
        return firstName;
    }


    public String getLastName()
    {
        return lastName;
    }


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


        public TesterData build()
        {
            return new TesterData(id, firstName, lastName, email);
        }
    }


    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}