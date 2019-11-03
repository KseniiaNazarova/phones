package com.phone.site.api;

import java.sql.Timestamp;
import java.util.Objects;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Phone history view
 */
public class PhoneHistory
{
    private final Long phoneId;

    private final Model model;

    private final Tester tester;

    private final Timestamp bookedOn;

    private final Timestamp returnedOn;


    @JsonCreator
    private PhoneHistory(@JsonProperty("phoneId") final Long phoneId,
                         @JsonProperty("model") final Model model,
                         @JsonProperty("tester") final Tester tester,
                         @JsonProperty("bookedOn") final Timestamp bookedOn,
                         @JsonProperty("returnedOn") @Nullable final Timestamp returnedOn)
    {
        this.phoneId = Objects.requireNonNull(phoneId);
        this.model = Objects.requireNonNull(model);
        this.tester = Objects.requireNonNull(tester);
        this.bookedOn = Objects.requireNonNull(bookedOn);
        this.returnedOn = returnedOn;
    }


    public static Builder builder()
    {
        return new Builder();
    }


    @JsonProperty("phoneId")
    public Long getPhoneId()
    {
        return phoneId;
    }


    @JsonProperty("model")
    public Model getModel()
    {
        return model;
    }


    @JsonProperty("tester")
    public Tester getTester()
    {
        return tester;
    }


    @JsonProperty("bookedOn")
    public Timestamp getBookedOn()
    {
        return bookedOn;
    }


    @Nullable
    @JsonProperty("returnedOn")
    public Timestamp getReturnedOn()
    {
        return returnedOn;
    }


    public static final class Builder
    {
        private Long phoneId;

        private Model model;

        private Tester tester;

        private Timestamp bookedOn;

        private Timestamp returnedOn;


        public Builder withPhoneId(final Long phoneId)
        {
            this.phoneId = phoneId;
            return this;
        }


        public Builder withModel(final Model model)
        {
            this.model = model;
            return this;
        }


        public Builder withTester(final Tester tester)
        {
            this.tester = tester;
            return this;
        }


        public Builder withBookedOn(final Timestamp bookedOn)
        {
            this.bookedOn = bookedOn;
            return this;
        }


        public Builder withReturnedOn(final Timestamp returnedOn)
        {
            this.returnedOn = returnedOn;
            return this;
        }


        public PhoneHistory build()
        {
            return new PhoneHistory(phoneId, model, tester, bookedOn, returnedOn);
        }
    }


    @Override
    public String toString()
    {
        return "PhoneHistory{" +
                "phoneId=" + phoneId +
                ", model=" + model +
                ", tester=" + tester +
                ", bookedOn=" + bookedOn +
                ", returnedOn=" + returnedOn +
                '}';
    }
}
