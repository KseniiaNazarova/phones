package com.phone.site.core.dao;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.dao.EmptyResultDataAccessException;

/**
 * Utils for interaction with db
 */
public class SqlUtils
{
    public static <ResultT> Optional<ResultT> getOptional(Supplier<ResultT> supplier)
    {
        try
        {
            return Optional.ofNullable(supplier.get());
        }
        catch (EmptyResultDataAccessException ex)
        {
            return Optional.empty();
        }
    }
}
