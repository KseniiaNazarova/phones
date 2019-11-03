package com.phone.site.api;


import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * View of a request to book a phone
 */
public class BookPhoneRequest
{
    private final PhoneId phoneId;

    private final TesterId testerId;


    @JsonCreator
    private BookPhoneRequest(@JsonProperty("phoneId") final PhoneId phoneId,
                             @JsonProperty("testerId") final TesterId testerId)
    {
        this.phoneId = Objects.requireNonNull(phoneId);
        this.testerId = Objects.requireNonNull(testerId);
    }


    public static Builder builder()
    {
        return new Builder();
    }


    @JsonProperty("phoneId")
    public PhoneId getPhoneId()
    {
        return phoneId;
    }


    @JsonProperty("testerId")
    public TesterId getTesterId()
    {
        return testerId;
    }


    public static final class Builder
    {
        private PhoneId phoneId;

        private TesterId testerId;


        public Builder withPhoneId(final PhoneId phoneId)
        {
            this.phoneId = phoneId;
            return this;
        }


        public Builder withTesterId(final TesterId testerId)
        {
            this.testerId = testerId;
            return this;
        }


        public BookPhoneRequest build()
        {
            return new BookPhoneRequest(phoneId, testerId);
        }
    }


    @Override
    public String toString()
    {
        return "BookPhoneRequest{" +
                "phoneId=" + phoneId +
                ", testerId=" + testerId +
                '}';
    }
}
