package com.phone.site.controller;

import com.phone.site.api.ErrorResponse;
import com.phone.site.core.excpetions.BookPhoneException;
import com.phone.site.core.excpetions.FonoapiRuntimeException;
import com.phone.site.core.excpetions.PhoneNotFoundException;
import com.phone.site.core.excpetions.ReturnPhoneException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class PhoneExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(value = {PhoneNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse phoneNotFoundException(Exception ex)
    {
        return new ErrorResponse(404, "Phone Not Found");
    }


    @ExceptionHandler(value = {BookPhoneException.class, ReturnPhoneException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse orderException(Exception ex)
    {
        return new ErrorResponse(404, "Cannot book or return a phone");
    }


    @ExceptionHandler(value = {FonoapiRuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse fonoapiException(Exception ex)
    {
        return new ErrorResponse(404, "Fonoapi exception");
    }
}