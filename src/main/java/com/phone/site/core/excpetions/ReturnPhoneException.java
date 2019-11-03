package com.phone.site.core.excpetions;

/**
 * Custom exceptions if a phone cannot be returned
 */
public class ReturnPhoneException extends RuntimeException
{
    public ReturnPhoneException(final Long phoneId)
    {
        super("Phone cannot be returned: phoneId=" + phoneId);
    }
}
