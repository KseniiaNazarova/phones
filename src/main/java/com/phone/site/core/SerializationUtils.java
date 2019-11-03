package com.phone.site.core;


import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class SerializationUtils
{
    private static final ObjectMapper MAPPER = (new ObjectMapper()).findAndRegisterModules()
            .configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false)
            .configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .setSerializationInclusion(Include.NON_NULL);


    public static <T> String toJson(T object)
    {
        try
        {
            return MAPPER.writeValueAsString(object);
        }
        catch (IOException var2)
        {
            throw new IllegalArgumentException("Can't serialize object [" + object + "]", var2);
        }
    }


    public static <T> T fromJson(String content, Class<T> clazz)
    {
        try
        {
            return MAPPER.readValue(content, clazz);
        }
        catch (IOException var3)
        {
            throw new IllegalArgumentException("Can't deserialize data [" + content + "]", var3);
        }
    }
}
