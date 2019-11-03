package com.phone.site.core.excpetions;

/**
 * Custom exceptions if a phone isn't found
 */
public class PhoneNotFoundException extends RuntimeException
{
    public PhoneNotFoundException(final Long id)
    {
        super("Phone not found: phoneId=" + id);
    }
}
