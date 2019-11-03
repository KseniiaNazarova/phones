package com.phone.site.core.excpetions;

/**
 * Custom exceptions if a phone cannot be booked
 */
public class BookPhoneException extends RuntimeException
{
    public BookPhoneException(final Long phoneId, final Long testerId)
    {
        super("Phone cannot be booked: phoneId=" + phoneId + ", testerId=" + testerId);
    }
}
