package com.phone.site.core;


import java.util.List;

import com.phone.site.api.Phone;
import com.phone.site.api.PhoneHistory;
import com.phone.site.api.PhoneId;
import com.phone.site.api.TesterId;


public interface PhoneService
{
    Phone getPhoneDetails(final PhoneId id);

    List<Phone> getPhones();

    void bookPhone(final PhoneId id, final TesterId testerId);

    void returnPhone(final PhoneId id);

    List<PhoneHistory> getPhoneHistory(final PhoneId phoneId);
}
