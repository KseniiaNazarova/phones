package com.phone.site.controller;

import java.util.List;

import javax.validation.Valid;

import com.phone.site.api.BookPhoneRequest;
import com.phone.site.api.Phone;
import com.phone.site.api.PhoneId;
import com.phone.site.core.PhoneService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/phones")
public class PhonesController
{
    private final PhoneService phoneService;


    public PhonesController(final PhoneService phoneService)
    {
        this.phoneService = phoneService;
    }


    @PostMapping("/get")
    public Phone getPhoneDetails(@RequestBody @Valid final PhoneId phoneId)
    {
        return phoneService.getPhoneDetails(phoneId);
    }


    @PostMapping("/all")
    public List<Phone> getPhones()
    {
        return phoneService.getPhones();
    }


    @PostMapping("/book")
    public void bookPhone(@RequestBody @Valid final BookPhoneRequest request)
    {
        phoneService.bookPhone(request.getPhoneId(), request.getTesterId());
    }


    @PostMapping("/return")
    public void returnPhone(@RequestBody @Valid final PhoneId phoneId)
    {
        phoneService.returnPhone(phoneId);
    }


    @PostMapping("/history")
    public void getHistory(@RequestBody @Valid final PhoneId phoneId)
    {
        phoneService.getPhoneHistory(phoneId);
    }
}
