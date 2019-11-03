package com.phone.site.core.dao.entity;

import java.sql.Timestamp;
import java.util.Objects;


public class PhoneHistoryData
{
    private final Long phoneId;

    private final ModelData modelData;

    private final TesterData tester;

    private final Timestamp bookedOn;

    private final Timestamp returnedOn;


    private PhoneHistoryData(final Long phoneId,
                             final ModelData modelData,
                             final TesterData tester,
                             final Timestamp bookedOn, final Timestamp returnedOn)
    {
        this.phoneId = Objects.requireNonNull(phoneId);
        this.modelData = Objects.requireNonNull(modelData);
        this.tester = Objects.requireNonNull(tester);
        this.bookedOn = Objects.requireNonNull(bookedOn);
        this.returnedOn = Objects.requireNonNull(returnedOn);
    }


    public static Builder builder()
    {
        return new Builder();
    }


    public Long getPhoneId()
    {
        return phoneId;
    }


    public ModelData getModelData()
    {
        return modelData;
    }


    public TesterData getTester()
    {
        return tester;
    }


    public Timestamp getBookedOn()
    {
        return bookedOn;
    }


    public Timestamp getReturnedOn()
    {
        return returnedOn;
    }


    public static final class Builder
    {
        private Long phoneId;

        private ModelData modelData;

        private TesterData tester;

        private Timestamp bookedOn;

        private Timestamp returnedOn;


        public Builder withPhoneId(final Long phoneId)
        {
            this.phoneId = phoneId;
            return this;
        }


        public Builder withModelData(final ModelData modelData)
        {
            this.modelData = modelData;
            return this;
        }


        public Builder withTester(final TesterData tester)
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


        public PhoneHistoryData build()
        {
            return new PhoneHistoryData(phoneId, modelData, tester, bookedOn, returnedOn);
        }
    }


    @Override
    public String toString()
    {
        return "PhoneHistoryData{" +
                "phoneId=" + phoneId +
                ", modelData=" + modelData +
                ", tester=" + tester +
                ", bookedOn=" + bookedOn +
                ", returnedOn=" + returnedOn +
                '}';
    }
}
