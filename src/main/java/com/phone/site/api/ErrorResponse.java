package com.phone.site.api;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Response that we get when we caught an exception
 */
public class ErrorResponse
{
    private final int status;

    private final String message;


    @JsonCreator
    public ErrorResponse(@JsonProperty("status") final int status,
                         @JsonProperty("message") final String message)
    {
        this.status = status;
        this.message = Objects.requireNonNull(message);
    }


    @JsonProperty("status")
    public int getStatus()
    {
        return status;
    }


    @JsonProperty("message")
    public String getMessage()
    {
        return message;
    }


    @Override
    public String toString()
    {
        return "ErrorResponse{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
