package com.phone.site.core.excpetions;

/**
 * Exceptions during interaction with fonoapi
 */
public class FonoapiRuntimeException extends RuntimeException
{
    public FonoapiRuntimeException()
    {
        super("Error while working with fonoapi");
    }
}
